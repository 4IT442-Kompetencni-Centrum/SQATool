package controllers;

import models.User;
import play.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }

    public static Result login(){
        return ok(login.render());
    }

}
