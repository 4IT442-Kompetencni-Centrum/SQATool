package controllers;


import daos.impl.DAOs;
import models.Activity;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.Configuration;
import views.html.activity.show;
import views.html.activity.showAll;

import java.util.List;

public class ActivityController extends Controller {

    @Transactional(readOnly=true)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Activity> activities = DAOs.getActivityDao().findAll(page, Configuration.PAGE_SIZE);

        Integer total = DAOs.getActivityDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total/Configuration.PAGE_SIZE : total/Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(activities, numberOfPages, page));
    }

    @Transactional(readOnly=true)
    public static Result show(Long activityId) {
        Activity activity = DAOs.getActivityDao().findById(activityId);

        if(activity == null)
            return notFound();

        return ok(show.render(activity));
    }


}
