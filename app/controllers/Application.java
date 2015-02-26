package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }

}
