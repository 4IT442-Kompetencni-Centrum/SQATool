package controllers;


import daos.impl.DAOs;
import forms.ActivityForm;
import models.Activity;
import models.TypeRoleOnActivity;
import models.User;
import models.UserLoggedOnActivity;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.ActionsEnum;
import service.AuthorizedAction.Authorize;
import service.Configuration;
import service.EnumerationWithKeys;
import service.SecurityService;
import views.data.MenuDto;
import views.html.activity.add;
import views.html.activity.edit;
import views.html.activity.show;
import views.html.activity.showAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Security.Authenticated(Secured.class)
public class ActivityController extends Controller {
    static Form<ActivityForm> activityForm = Form.form(ActivityForm.class);


    @Transactional(readOnly = true)
    @Authorize(action = ActionsEnum.ACTIVITY_SHOW_ALL)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Activity> activities = DAOs.getActivityDao().findAll(page*Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);

        Integer total = DAOs.getActivityDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total / Configuration.PAGE_SIZE : total / Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(activities, numberOfPages, page, getMainMenu()));
    }

    @Transactional(readOnly = true)
    @Authorize(action = ActionsEnum.ACTIVITY_SHOW)
    public static Result show(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);

        if (activity == null)
            return notFound();

        User organizer = DAOs.getUserLoggedOnActivityDao().getOrganizer(activity);
        List<User> loggedUsers = DAOs.getUserLoggedOnActivityDao().getLoggedUsers(activity);

        User currentUser = SecurityService.fetchUser(session("authid"));
        Boolean isLogged = DAOs.getUserLoggedOnActivityDao().isLogged(activity, currentUser);


        return ok(show.render(activity, getBackToListMenu(),organizer,loggedUsers, isLogged));
    }

    @Transactional(readOnly = false)
    @Authorize(action = ActionsEnum.ACTIVITY_DELETE)
    public static Result delete(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);
        if (activity == null)
            return notFound();

        DAOs.getActivityDao().delete(activity);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }

    @Transactional(readOnly = true)
    @Authorize(action = ActionsEnum.ACTIVITY_ADD)
    public static Result add() {
        Map<String, String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        return ok(add.render(activityForm, activityTypes, getBackToListMenu()));
    }

    @Transactional(readOnly = false)
    @Authorize(action = ActionsEnum.ACTIVITY_ADD)
    public static Result create() {
        Form<ActivityForm> form = activityForm.bindFromRequest();
        Map<String, String> activityTypes = DAOs.getTypeActivityDao().getOptions();


        if (form.hasErrors()) {
            return badRequest(add.render(form, activityTypes, getBackToListMenu()));
        }

        Activity activity = form.get().getActivity();
        DAOs.getActivityDao().create(activity);

        User user = SecurityService.fetchUser(session("authid"));
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_ORGANIZER);

        UserLoggedOnActivity item = new UserLoggedOnActivity();
        item.setUser(user);
        item.setActivity(activity);
        item.setTypeRoleOnActivity(role);

        DAOs.getUserLoggedOnActivityDao().create(item);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }

    @Transactional(readOnly = true)
    @Authorize(action = ActionsEnum.ACTIVITY_EDIT)
    public static Result edit(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);
        Map<String, String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        if (activity == null)
            return notFound();

        Form<ActivityForm> form = activityForm.fill(new ActivityForm(activity));

        return ok(edit.render(form, activityTypes, getBackToListMenu()));
    }

    @Transactional(readOnly = false)
    @Authorize(action = ActionsEnum.ACTIVITY_EDIT)
    public static Result update() {
        Form<ActivityForm> form = activityForm.bindFromRequest();
        Map<String, String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        if (form.hasErrors()) {
            return badRequest(edit.render(form, activityTypes, getBackToListMenu()));
        }

        Activity activity = form.get().getActivity();
        DAOs.getActivityDao().update(activity);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }


    /**
     * Method logs current user to activity
     *
     * @param Long activityId
     * @return
     */
    @Transactional(readOnly = false)
    public static Result logIn(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);

        if(activity == null)
            return notFound();

        User user = SecurityService.fetchUser(session("authid"));
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);

        UserLoggedOnActivity item = new UserLoggedOnActivity();
        item.setUser(user);
        item.setActivity(activity);
        item.setTypeRoleOnActivity(role);

        DAOs.getUserLoggedOnActivityDao().create(item);

        return redirect(controllers.routes.ActivityController.show(activity.getActivityId()));
    }

    @Transactional(readOnly = false)
    public static Result logOut(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);

        if(activity == null)
            return notFound();

        User user = SecurityService.fetchUser(session("authid"));
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);
        UserLoggedOnActivity item = DAOs.getUserLoggedOnActivityDao().find(activity, user, role);

        DAOs.getUserLoggedOnActivityDao().delete(item);

        return redirect(controllers.routes.ActivityController.show(activity.getActivityId()));
    }

    /**
     * Method returns list of items to left side menu. This implementation returns one item - back to list
     *
     * @return
     */
    private static List<MenuDto> getBackToListMenu() {
        List<MenuDto> result = new ArrayList<MenuDto>();
        User user = SecurityService.fetchUser(session("authid"));
        if (SecurityService.hasAccess(user, ActionsEnum.ACTIVITY_SHOW_ALL)) {
            MenuDto back = new MenuDto();
            back.setGlyphicon("triangle-left");
            back.setLabel("Zpět na seznam aktivit");
            back.setUrl(routes.ActivityController.showAll(0).absoluteURL(request()));
            result.add(back);
        }

        return result;
    }

    /**
     * Method returns list of items to left side menu. This implementation returns one item - add new
     *
     * @return
     */
    private static List<MenuDto> getMainMenu() {
        List<MenuDto> result = new ArrayList<MenuDto>();
        User user = SecurityService.fetchUser(session("authid"));
        if (SecurityService.hasAccess(user, ActionsEnum.ACTIVITY_ADD)) {
            MenuDto newActivity = new MenuDto();
            newActivity.setGlyphicon("plus");
            newActivity.setLabel("Přidat aktivitu");
            newActivity.setUrl(routes.ActivityController.add().absoluteURL(request()));
            result.add(newActivity);
        }

        return result;
    }


}
