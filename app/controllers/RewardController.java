package controllers;


import daos.impl.DAOs;
import forms.RewardForm;
import models.Reward;
import play.data.Form;
import play.mvc.*;
import play.db.jpa.Transactional;
import service.Configuration;
import views.data.MenuDto;
import views.html.reward.edit;
import views.html.reward.add;
import views.html.reward.show;
import views.html.reward.showAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Security.Authenticated(Secured.class)
public class RewardController extends Controller {
    
    static Form<RewardForm> rewardForm = Form.form(RewardForm.class);

    @Transactional(readOnly=true)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Reward> rewards = DAOs.getRewardDao().findAll(page, Configuration.PAGE_SIZE);

        Integer total = DAOs.getRewardDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total/Configuration.PAGE_SIZE : total/Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(rewards, numberOfPages, page, getMainMenu()));
    }


    @Transactional(readOnly=true)
    public static Result show(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);

        if(reward == null)
            return notFound();

        return ok(show.render(reward, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    public static Result delete(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);
        if(reward == null)
            return notFound();

        DAOs.getRewardDao().delete(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }

    @Transactional(readOnly=true)
    public static Result add() {
        Map users = DAOs.getUserDao().getUsersForSelectBox();

        return ok(add.render(rewardForm, users, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    public static Result create() {
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if(form.hasErrors())
        {
            Map users = DAOs.getUserDao().getUsersForSelectBox();
            return badRequest(add.render(form,users,getBackToListMenu()));
        }

        Reward reward = form.get().getReward();
        DAOs.getRewardDao().create(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }

    @Transactional(readOnly=true)
    public static Result edit(Long rewardId){
        Reward reward = DAOs.getRewardDao().findById(rewardId);

        if(reward == null)
            return notFound();

        Map users = DAOs.getUserDao().getUsersForSelectBox();
        Form<RewardForm> form = rewardForm.fill(new RewardForm(reward));
        
        return ok(edit.render(form, users, getBackToListMenu()));
    }

    @Transactional(readOnly=false)
    public static Result update(){
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if(form.hasErrors())
        {
            Map users = DAOs.getUserDao().getUsersForSelectBox();
            return badRequest(edit.render(form, users, getBackToListMenu()));
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

        MenuDto back = new MenuDto();
        back.setGlyphicon("triangle-left");
        back.setLabel("Zpět na seznam odměn");
        back.setUrl(routes.RewardController.showAll(0).absoluteURL(request()));
        result.add(back);

        return result;
    }

    /**
     * Method returns list of items to left side menu. This implementation returns one item - add new
     * @return
     */
    private static List<MenuDto> getMainMenu() {
        List<MenuDto> result = new ArrayList<MenuDto>();

        MenuDto newReward = new MenuDto();
        newReward.setGlyphicon("plus");
        newReward.setLabel("Přidat odměnu");
        newReward.setUrl(routes.RewardController.add().absoluteURL(request()));
        result.add(newReward);

        return result;
    }

}
