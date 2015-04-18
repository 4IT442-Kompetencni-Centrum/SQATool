package forms;


import daos.impl.DAOs;
import models.Reward;
import models.User;
import play.data.validation.Constraints;

import java.util.Date;

public class RewardForm {

    protected Long rewardId;

    @Constraints.Required
    protected Integer amount;

    protected Date date;

    protected String description;

    @Constraints.Required
    private Long userId;

    public Long getRewardId() {
        return rewardId;
    }


    public RewardForm() {

    }

    public RewardForm(Reward reward) {
        this.rewardId = reward.getRewardId();
        this.amount = reward.getAmount();
        this.date = reward.getDate();
        this.description = reward.getDescription();

        if (reward.getUser() != null)
            this.userId = reward.getUser().id;
    }

    public RewardForm setRewardId(Long rewardId) {
        this.rewardId = rewardId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public RewardForm setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public RewardForm setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RewardForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RewardForm setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Reward getReward() {
        Reward reward = null;

        if (this.getRewardId() != null)
            reward = DAOs.getRewardDao().findById(this.getRewardId());

        if (reward == null)
            reward = new Reward();

        reward.setAmount(this.getAmount());

        if (this.getDate() == null)
            reward.setDate(new Date());
        else
            reward.setDate(this.getDate());

        reward.setDescription(this.getDescription());

        if (this.getUserId() != null) {
            User user = DAOs.getUserDao().findById(this.getUserId());

            if (user != null)
                reward.setUser(user);

        }

        return reward;
    }
}
