package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.accessDenied;
import views.html.index;

@Security.Authenticated(Secured.class)
public class Application extends Controller {
	
    public static Result index() {
        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
    
    public static Result accessDenied() {
    	return ok(accessDenied.render());
    }
    
}
