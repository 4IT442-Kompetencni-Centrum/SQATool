package controllers;


import daos.impl.DAOs;
import forms.KnowledgeForm;
import forms.UsersForm;
import models.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.SecurityService;
import views.html.profile.*;

import java.util.List;
import java.util.Set;

@Security.Authenticated(Secured.class)
public class ProfileController extends Controller {
    static Form<UsersForm> usersForm = Form.form(UsersForm.class);
    static Form<KnowledgeForm> knowledgeForm = Form.form(KnowledgeForm.class);

    @Transactional(readOnly = true)
    public static Result userDetail() {
        User user = SecurityService.fetchUser(session("authid"));
        return ok(userDetail.render(user, DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = true)
    public static Result editUserDetail() {
        User user = SecurityService.fetchUser(session("authid"));
        Form<UsersForm> form = usersForm.fill(new UsersForm(user));

        return ok(editUserDetail.render(form,DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = false)
    public static Result updateUserDetail() {
        Form<UsersForm> form = usersForm.bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(editUserDetail.render(form, DashboardController.getMainMenu("userDetail")));
        }

        User user = form.get().getUser();
        DAOs.getUserDao().update(user);

        return redirect(routes.ProfileController.userDetail());
    }


    @Transactional(readOnly = true)
    public static Result knowledge() {
        User user = SecurityService.fetchUser(session("authid"));

        List<Knowledge> knowledges = user.getKnowledges();

        return ok(knowledge.render(knowledges, DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = true)
    public static Result editKnowledge() {
        List<TypeKnowledge> knowledgeTypes = DAOs.getTypeKnowledgeDao().findAll();
        List<LevelOfKnowledge> knowledgeLevels = DAOs.getLevelOfKnowledgeDao().findAll();

        User user = SecurityService.fetchUser(session("authid"));
        List<Knowledge> knowledges = user.getKnowledges();

        return ok(editKnowledge.render(knowledgeTypes, knowledgeLevels, knowledges, DashboardController.getMainMenu("userDetail")));
    }



    @Transactional(readOnly = false)
    public static Result updateKnowledge() {
        Form<KnowledgeForm> form = knowledgeForm.bindFromRequest();
        
        List<Knowledge> knowledges = form.get().getKnowledges();

        User user = SecurityService.fetchUser(session("authid"));

        List<Knowledge> currentKnowledges = user.getKnowledges();

        for(Knowledge knowledge : knowledges) {
            if(knowledge.getUserHasKnowledgeId() == null) {
                DAOs.getKnowledgeDao().create(knowledge);
            } else {
                DAOs.getKnowledgeDao().update(knowledge);
            }
        }

        for(Knowledge current : currentKnowledges) {
            if(!knowledges.contains(current)) {
                DAOs.getKnowledgeDao().delete(current);
            }
        }

        user.setKnowledges(knowledges);
        DAOs.getUserDao().update(user);


        return redirect(routes.ProfileController.knowledge());
    }

    @Transactional(readOnly = true)
    public static Result academicWorks() {
        User user = SecurityService.fetchUser(session("authid"));

        Set<AcademicWork> worksList = user.getAcademicWorks();

        return ok(academicWorks.render(worksList,DashboardController.getMainMenu("userDetail")));
    }


}
