package controllers;


import daos.impl.DAOs;
import forms.RewardForm;
import models.Project;
import models.Reward;
import models.User;
import play.data.Form;
import play.mvc.*;
import play.db.jpa.Transactional;
import service.ActionsEnum;
import service.AuthorizedAction.Authorize;
import service.Configuration;
import service.SecurityService;
import views.data.MenuDto;
import views.html.notFound;
import views.html.reward.edit;
import views.html.reward.add;
import views.html.reward.show;
import views.html.reward.showAll;
import views.html.user.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Security.Authenticated(Secured.class)
public class RewardController extends Controller {
    
    static Form<RewardForm> rewardForm = Form.form(RewardForm.class);

    @Transactional(readOnly=true)
    @Authorize(action = ActionsEnum.ACTIVITY_SHOW_ALL)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Reward> rewards = DAOs.getRewardDao().findAll(page*Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);

        Integer total = DAOs.getRewardDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total/Configuration.PAGE_SIZE : total/Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(rewards, numberOfPages, page, getMainMenu("rewards")));
    }


    @Transactional(readOnly=true)
    @Authorize(action = ActionsEnum.ACTIVITY_SHOW)
    public static Result show(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);

        if(reward == null)
            return notFound(notFound.render());

        return ok(show.render(reward, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    @Authorize(action = ActionsEnum.ACTIVITY_DELETE)
    public static Result delete(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);
        if(reward == null)
            return notFound(notFound.render());

        DAOs.getRewardDao().delete(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }

    @Transactional(readOnly=true)
    @Authorize(action = ActionsEnum.ACTIVITY_ADD)
    public static Result add(Long userId) {
        User user = DAOs.getUserDao().findById(userId);

        if(user == null)
            return notFound(notFound.render());

        List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(userId);

        return ok(add.render(rewardForm, user, projects, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    @Authorize(action = ActionsEnum.ACTIVITY_ADD)
    public static Result create() {
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if (form.hasErrors()) {
            User user = DAOs.getUserDao().findById(Long.parseLong(form.field("userId").value()));
            List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(user.getId());

            return badRequest(detail.render(user, projects, form, getBackToListMenu(), false, false));
        }

        Reward reward = form.get().getReward();
        DAOs.getRewardDao().create(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }

    @Transactional(readOnly=true)
    @Authorize(action = ActionsEnum.ACTIVITY_EDIT)
    public static Result edit(Long rewardId){
        Reward reward = DAOs.getRewardDao().findById(rewardId);

        if(reward == null)
            return notFound(notFound.render());

        Form<RewardForm> form = rewardForm.fill(new RewardForm(reward));
        List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(reward.getUser().getId());

        return ok(edit.render(form, reward.getUser(), projects, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    @Authorize(action = ActionsEnum.ACTIVITY_EDIT)
    public static Result update(){
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if(form.hasErrors())
        {
            User user = DAOs.getUserDao().findById(Long.parseLong(form.field("userId").value()));
            List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(user.getId());

            return badRequest(edit.render(form, user, projects, getBackToListMenu()));
        }

        Reward reward = form.get().getReward();
        DAOs.getRewardDao().update(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }


    /**
     * Method returns list of items to left side menu. This implementation returns one item - back to list
     * @return
     */
    private static List<MenuDto> getBackToListMenu() {
        List<MenuDto> result = new ArrayList<MenuDto>();
        User user = SecurityService.fetchUser(session("authid"));

        if (SecurityService.hasAccess(user, ActionsEnum.REWARD_SHOW_ALL)) {
            MenuDto back = new MenuDto();
            back.setGlyphicon("triangle-left");
            back.setLabel("Zpět na seznam odměn");
            back.setUrl(routes.RewardController.showAll(0).absoluteURL(request()));
            result.add(back);
        }

        return result;
    }

    /**
     * Method returns list of items to left side menu. This implementation returns one item - add new
     * @return
     */
    protected static List<MenuDto> getMainMenu(String selected) {
       return DashboardController.getMainMenu(selected);
    }

    protected static List<MenuDto> getMainMenu() {
       return DashboardController.getMainMenu();
    }

}
