package controllers;


import daos.impl.DAOs;
import forms.RewardForm;
import models.Reward;
import play.data.Form;
import play.mvc.*;
import play.db.jpa.Transactional;
import service.Configuration;
import views.html.reward.edit;
import views.html.reward.newItem;
import views.html.reward.show;
import views.html.reward.showAll;

import java.util.List;

public class RewardController extends Controller {
    
    static Form<RewardForm> rewardForm = Form.form(RewardForm.class);

    @Transactional(readOnly=true)
    public static Result showAll(Integer page) {
        page = page != null ? page : 0;

        List<Reward> rewards = DAOs.getRewardDao().findAll(page, Configuration.PAGE_SIZE);

        Integer total = DAOs.getRewardDao().count();
        Integer numberOfPages = total % Configuration.PAGE_SIZE == 0 ? total/Configuration.PAGE_SIZE : total/Configuration.PAGE_SIZE + 1;


        return ok(showAll.render(rewards, numberOfPages, page));
    }


    @Transactional(readOnly=true)
    public static Result show(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);

        if(reward == null)
            return notFound();

        return ok(show.render(reward));
    }

    @Transactional(readOnly=false)
    public static Result delete(Long rewardId) {
        Reward reward = DAOs.getRewardDao().findById(rewardId);
        if(reward == null)
            return notFound();

        DAOs.getRewardDao().delete(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }

    public static Result newItem() {
        return ok(newItem.render(rewardForm));
    }

    @Transactional(readOnly=false)
    public static Result create() {
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if(form.hasErrors())
        {
            return badRequest(newItem.render(form));
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

        Form<RewardForm> form = rewardForm.fill(new RewardForm(reward));
        
        return ok(edit.render(form));
    }

    @Transactional(readOnly=false)
    public static Result update(){
        Form<RewardForm> form = rewardForm.bindFromRequest();

        if(form.hasErrors())
        {
            return badRequest(edit.render(form));
        }

        Reward reward = form.get().getReward();
        DAOs.getRewardDao().update(reward);

        return redirect(controllers.routes.RewardController.showAll(0));
    }
}
