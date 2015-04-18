package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Petr Kadlec on 18.4.2015.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("user");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx){
        return redirect(routes.Login.showLoginPage());
    }
}
