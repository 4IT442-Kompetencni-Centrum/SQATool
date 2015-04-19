package service;

import controllers.*;
import models.User;
import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class AuthorizedAction extends Action<AuthorizedAction.Authorize> {
    @Override
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        ActionsEnum action = configuration.action();
        User user = SecurityService.fetchUser(ctx.session().get("authid"));
        Logger.debug(action.toString());
        if (!SecurityService.hasAccess(user, action)) {
            if(configuration.redirect()) {
                return F.Promise.pure(redirect(routes.Application.accessDenied()));
            }
            return null;
        }

        return delegate.call(ctx);
    }


    @With(AuthorizedAction.class)
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authorize {
        ActionsEnum action();
        boolean redirect() default true;
    }
}