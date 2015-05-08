package controllers;


import daos.impl.DAOs;
import forms.LevelOfKnowledgeForm;
import forms.TypeKnowledgeForm;
import models.LevelOfKnowledge;
import models.TypeKnowledge;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.admin.enumerations;
import views.html.admin.levelKnowledge;
import views.html.admin.typeKnowledge;

import java.util.List;

@Security.Authenticated(Secured.class)
public class AdminController extends Controller {
    static Form<TypeKnowledgeForm> typeKnowledgeForm = Form.form(TypeKnowledgeForm.class);
    static Form<LevelOfKnowledgeForm> levelOfKnowledgeForm = Form.form(LevelOfKnowledgeForm.class);

    @Transactional(readOnly = true)
    public static Result enumerations() {
        return ok(enumerations.render(DashboardController.getMainMenu()));
    }


    @Transactional(readOnly = true)
    public static Result editKnowledgeTypes() {
        List<TypeKnowledge> types = DAOs.getTypeKnowledgeDao().findAll();

        return ok(typeKnowledge.render(types, DashboardController.getMainMenu()));
    }


    @Transactional(readOnly = false)
    public static Result updateKnowledgeTypes() {
        Form<TypeKnowledgeForm> form = typeKnowledgeForm.bindFromRequest();

        if(form.hasErrors()) {
            List<TypeKnowledge> types = DAOs.getTypeKnowledgeDao().findAll();

            return badRequest(typeKnowledge.render(types, DashboardController.getMainMenu()));
        }

        List<TypeKnowledge> list = form.get().getList();
        List<TypeKnowledge> currentList = DAOs.getTypeKnowledgeDao().findAll();

        for(TypeKnowledge typeKnowledge : list) {
            if(typeKnowledge.getTypeKnowledgeId() != null) {
                DAOs.getTypeKnowledgeDao().update(typeKnowledge);
            } else {
                DAOs.getTypeKnowledgeDao().create(typeKnowledge);
            }
        }

        for(TypeKnowledge current : currentList) {
            if(!list.contains(current)) {
                DAOs.getTypeKnowledgeDao().delete(current);
            }
        }

        return redirect(controllers.routes.AdminController.editKnowledgeTypes());
    }

    @Transactional(readOnly = true)
    public static Result editKnowledgeLevels() {
        List<LevelOfKnowledge> levels = DAOs.getLevelOfKnowledgeDao().findAll();

        return ok(levelKnowledge.render(levels, DashboardController.getMainMenu()));
    }


    @Transactional(readOnly = false)
    public static Result updateKnowledgeLevels() {
        Form<LevelOfKnowledgeForm> form = levelOfKnowledgeForm.bindFromRequest();

        if(form.hasErrors()) {
            List<LevelOfKnowledge> levels = DAOs.getLevelOfKnowledgeDao().findAll();

            return badRequest(levelKnowledge.render(levels, DashboardController.getMainMenu()));
        }

        List<LevelOfKnowledge> list = form.get().getList();
        List<LevelOfKnowledge> currentList = DAOs.getLevelOfKnowledgeDao().findAll();

        for(LevelOfKnowledge levelOfKnowledge : list) {
            if(levelOfKnowledge.getLevelOfKnowledgeId() != null) {
                DAOs.getLevelOfKnowledgeDao().update(levelOfKnowledge);
            } else {
                DAOs.getLevelOfKnowledgeDao().create(levelOfKnowledge);
            }
        }

        for(LevelOfKnowledge current : currentList) {
            if(!list.contains(current)) {
                DAOs.getLevelOfKnowledgeDao().delete(current);
            }
        }

        return redirect(controllers.routes.AdminController.editKnowledgeLevels());
    }

}
