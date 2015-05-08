package controllers;


import daos.impl.DAOs;
import forms.AcademicWorkForm;
import models.AcademicWork;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.SecurityService;
import views.html.academicWork.add;
import views.html.academicWork.edit;
import views.html.notFound;

import java.util.Map;

@Security.Authenticated(Secured.class)
public class AcademicWorkController extends Controller{

    static Form<AcademicWorkForm> academicWorkForm = Form.form(AcademicWorkForm.class);


    @Transactional(readOnly = true)
    public static Result add() {
        Map types = DAOs.getTypeAcademicWorkDao().getOptions();
        Map states = DAOs.getStateAcademicWorkDao().getOptions();

        return ok(add.render(academicWorkForm,types,states,DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = false)
    public static Result create() {
        Form<AcademicWorkForm> form = academicWorkForm.bindFromRequest();

        if(form.hasErrors()) {
            Map types = DAOs.getTypeAcademicWorkDao().getOptions();
            Map states = DAOs.getStateAcademicWorkDao().getOptions();
            return badRequest(add.render(form,types,states,DashboardController.getMainMenu("userDetail")));
        }

        AcademicWork academicWork = form.get().getAcademicWork();

        DAOs.getAcademicWorkDao().create(academicWork);

        User user = SecurityService.fetchUser(session("authid"));
        user.addAcademicWork(academicWork);
        DAOs.getUserDao().update(user);

        return redirect(controllers.routes.ProfileController.academicWorks());
    }


    @Transactional(readOnly = true)
    public static Result edit(Long id) {
        AcademicWork academicWork = DAOs.getAcademicWorkDao().findById(id);
        Map types = DAOs.getTypeAcademicWorkDao().getOptions();
        Map states = DAOs.getStateAcademicWorkDao().getOptions();

        if(academicWork == null) {
            return notFound(notFound.render());
        }

        Form<AcademicWorkForm> form = academicWorkForm.fill(new AcademicWorkForm(academicWork));

        return ok(edit.render(form, types, states, DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = false)
    public static Result update() {
        Form<AcademicWorkForm> form = academicWorkForm.bindFromRequest();

        if (form.hasErrors()) {
            Map types = DAOs.getTypeAcademicWorkDao().getOptions();
            Map states = DAOs.getStateAcademicWorkDao().getOptions();
            return badRequest(edit.render(form, types, states, DashboardController.getMainMenu("userDetail")));
        }

        AcademicWork academicWork = form.get().getAcademicWork();
        DAOs.getAcademicWorkDao().update(academicWork);

        return redirect(controllers.routes.ProfileController.academicWorks());
    }


    @Transactional(readOnly = false)
    public static Result delete(Long id) {
        AcademicWork academicWork = DAOs.getAcademicWorkDao().findById(id);

        if(academicWork == null) {
            return notFound(notFound.render());
        }

        DAOs.getAcademicWorkDao().delete(academicWork);
        User user = SecurityService.fetchUser(session("authid"));
        user.removeAcademicWork(academicWork);
        DAOs.getUserDao().update(user);

        return redirect(controllers.routes.ProfileController.academicWorks());
    }
}
