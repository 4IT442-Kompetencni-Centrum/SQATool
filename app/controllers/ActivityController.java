package controllers;


import daos.impl.DAOs;
import forms.ActivityForm;
import models.Activity;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.Configuration;
import views.html.activity.add;
import views.html.activity.edit;
import views.html.activity.show;
import views.html.activity.showAll;

import java.util.List;
import java.util.Map;

public class ActivityController extends Controller {
    static Form<ActivityForm> activityForm = Form.form(ActivityForm.class);


    @Transactional(readOnly = true)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Activity> activities = DAOs.getActivityDao().findAll(page, Configuration.PAGE_SIZE);

        Integer total = DAOs.getActivityDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total / Configuration.PAGE_SIZE : total / Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(activities, numberOfPages, page));
    }

    @Transactional(readOnly = true)
    public static Result show(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);

        if (activity == null)
            return notFound();

        return ok(show.render(activity));
    }

    @Transactional(readOnly = false)
    public static Result delete(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);
        if (activity == null)
            return notFound();

        DAOs.getActivityDao().delete(activity);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }

    @Transactional(readOnly = true)
    public static Result add() {
        Map<String,String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        return ok(add.render(activityForm, activityTypes));
    }

    @Transactional(readOnly = false)
    public static Result create() {
        Form<ActivityForm> form = activityForm.bindFromRequest();
        Map<String,String> activityTypes = DAOs.getTypeActivityDao().getOptions();


        if (form.hasErrors()) {
            return badRequest(add.render(form,activityTypes));
        }

        Activity activity = form.get().getActivity();
        DAOs.getActivityDao().create(activity);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }

    @Transactional(readOnly = true)
    public static Result edit(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);
        Map<String,String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        if (activity == null)
            return notFound();

        Form<ActivityForm> form = activityForm.fill(new ActivityForm(activity));

        return ok(edit.render(form,activityTypes));
    }

    @Transactional(readOnly = false)
    public static Result update() {
        Form<ActivityForm> form = activityForm.bindFromRequest();
        Map<String,String> activityTypes = DAOs.getTypeActivityDao().getOptions();

        if (form.hasErrors()) {
            return badRequest(edit.render(form,activityTypes));
        }

        Activity activity = form.get().getActivity();
        DAOs.getActivityDao().update(activity);

        return redirect(controllers.routes.ActivityController.showAll(0));
    }

}
