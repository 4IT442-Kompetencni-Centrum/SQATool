package controllers;

import daos.UserDao;
import daos.impl.DAOs;
import daos.impl.UserDaoImpl;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.formData.LoginForm;
import views.html.index;
import views.html.login;

/**
 * Created by Petr Kadlec on 29.3.2015.
 */
public class Login extends Controller {

    private static UserDao userDao = DAOs.getUserDao();

    public static Result showLoginPage(){
        return ok(login.render());
    }

    @Transactional
    public static Result authenticate(){
        Form<LoginForm> bindForm = Form.form(LoginForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            flash("error", "Login credentials required.");
            return redirect(routes.Login.showLoginPage());
        }
        LoginForm filledForm = bindForm.get();
        User candidate = userDao.getValidUser(filledForm.username, filledForm.password);
        if(candidate == null){
            flash("error", "Wrong username or password");
            return redirect(routes.Login.showLoginPage());
        }
        session().clear();
        session("user",candidate.toString()); // CREATE THE VERY SECRET TOKEN
        session("authid", candidate.id.toString());
        return ok(index.render("SQA"));
    }

    public static Result logout(){
        session().clear();
        flash("logout", "You have been successfully logout");
        return redirect(routes.Login.showLoginPage());
    }
}