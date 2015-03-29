package controllers;

import daos.UserDao;
import daos.impl.DAOs;
import daos.impl.UserDaoImpl;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formData.LoginForm;

/**
 * Created by Petr Kadlec on 29.3.2015.
 */
public class Login extends Controller {

    private static UserDao userDao = DAOs.getUserDao();

    public static Result authenticate(){
        Form<LoginForm> bindForm = Form.form(LoginForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            flash("error", "Login credentials required.");
            return redirect(routes.Application.showLoginPage());
        }
        LoginForm filledForm = bindForm.get();
        User candidate = new User(filledForm.userName, filledForm.password);
        if(userDao.getValidUser(candidate.userName, candidate.password) == null){
            flash("error", "Wrong username or password");
            return redirect(routes.Application.showLoginPage());
        }
        session().clear();
        session("user", "secretToken" + bindForm.get().userName); // CREATE THE VERY SECRET TOKEN
        return TODO;
    }

    public static Result logout(){
        session().clear();
        flash("logout", "You have been successfully logout");
        return redirect(routes.Application.showLoginPage());
    }
}
