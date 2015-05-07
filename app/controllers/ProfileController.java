package controllers;


import daos.impl.DAOs;
import forms.UsersForm;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.SecurityService;
import views.html.profile.editKnowledge;
import views.html.profile.knowledge;
import views.html.profile.userDetail;
import views.html.profile.editUserDetail;

public class ProfileController extends Controller {
    static Form<UsersForm> usersForm = Form.form(UsersForm.class);

    @Transactional(readOnly = true)
    public static Result userDetail() {
        User user = SecurityService.fetchUser(session("authid"));
        return ok(userDetail.render(user, DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = true)
    //@Authorize(action = ActionsEnum.USER_EDIT_PROFILE)
    public static Result editUserDetail() {
        User user = SecurityService.fetchUser(session("authid"));
        Form<UsersForm> form = usersForm.fill(new UsersForm(user));

        return ok(editUserDetail.render(form,DashboardController.getMainMenu("userDetail")));
    }


    @Transactional(readOnly = false)
    //@Authorize(action = ActionsEnum.USER_EDIT_PROFILE)
    public static Result updateUserDetail() {
        Form<UsersForm> form = usersForm.bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(editUserDetail.render(form, DashboardController.getMainMenu("userDetail")));
        }

        User user = form.get().getUser();
        DAOs.getUserDao().update(user);

        return redirect(routes.ProfileController.userDetail());
    }
}
