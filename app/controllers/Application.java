package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.accessDenied;
import views.html.index;
import java.io.File;

public class Application extends Controller {
	
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result accessDenied() {
    	return ok(accessDenied.render());
    }
    
    public static Result resetDb() {
        File h2 = new File("db.h2.db");
        File lock = new File("db.lock.db");
        File trace = new File("db.trace.db");

        h2.delete();
        lock.delete();
        trace.delete();

        return redirect("@evolutions/apply/default");
    }
}
