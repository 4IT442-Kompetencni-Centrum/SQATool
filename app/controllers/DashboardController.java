package controllers;


import java.util.ArrayList;
import java.util.List;

import models.Activity;
import models.Reward;
import models.User;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.ActionsEnum;
import service.Configuration;
import service.ProjectConverter;
import service.SecurityService;
import views.data.MenuDto;
import views.html.dashboard.activities;
import views.html.dashboard.projects;
import views.html.dashboard.rewards;
import daos.impl.DAOs;


@Security.Authenticated(Secured.class)
public class DashboardController extends Controller {

    @Transactional(readOnly = true)
    public static Result activities(Integer page) {
        User user = SecurityService.fetchUser(session("authid"));

        List<Activity> activityList = DAOs.getUserLoggedOnActivityDao().findByUser(user, page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);

        Integer total = DAOs.getUserLoggedOnActivityDao().countUserActivities(user);
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total / Configuration.PAGE_SIZE : total / Configuration.PAGE_SIZE + 1;


        return ok(activities.render(activityList, getMainMenu("dashboard"), page, numberOfPages));
    }


    @Transactional(readOnly = true)
    public static Result rewards(Integer page) {
        User user = SecurityService.fetchUser(session("authid"));

        List<Reward> rewardList = DAOs.getRewardDao().findByUser(user, page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);

        Integer total = DAOs.getRewardDao().countUsersRewards(user);
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total / Configuration.PAGE_SIZE : total / Configuration.PAGE_SIZE + 1;


        return ok(rewards.render(rewardList, getMainMenu("dashboard"), page, numberOfPages));
    }
    /**
     * Action shows dashboard tab with projects and hours worked
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public static Result projects(Integer page) {
        User user = SecurityService.fetchUser(session("authid"));

        List<Object[]> projectList = DAOs.getProjectDao().getProjectsForUser(user, page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);

        Integer total = DAOs.getProjectDao().getNumberOfProjectsForUser(user);
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total / Configuration.PAGE_SIZE : total / Configuration.PAGE_SIZE + 1;

        return ok(projects.render(ProjectConverter.convertListOfObjectToDto(projectList, user), getMainMenu("dashboard"), page, numberOfPages));
    }


    /**
     * Method returns list of items to left side menu.
     *
     * @return
     */
    protected static List<MenuDto> getMainMenu(String selected) {
        List<MenuDto> result = new ArrayList<MenuDto>();
        User user = SecurityService.fetchUser(session("authid"));

        MenuDto newActivity = new MenuDto();
        newActivity.setGlyphicon("tasks");
        newActivity.setLabel("Dashboard");
        newActivity.setUrl(routes.DashboardController.activities(0).absoluteURL(request()));

        if(selected.equals("dashboard")) {
            newActivity.setSelected(true);
        }
        result.add(newActivity);


        if (SecurityService.hasAccess(user, ActionsEnum.REWARD_SHOW_ALL)) {
            MenuDto rewards = new MenuDto();
            rewards.setGlyphicon("usd");
            rewards.setLabel("Odměny");
            rewards.setUrl(routes.RewardController.showAll(0).absoluteURL(request()));

            if(selected.equals("rewards")) {
                rewards.setSelected(true);
            }

            result.add(rewards);
        }


        return result;
    }

    protected static List<MenuDto> getMainMenu() {
        return getMainMenu("");
    }
}
