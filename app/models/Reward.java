package models;

import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Reward Entity
 *
 * @author Aleš Jiránek <a.jiranek@centrum.cz>
 */
@Entity
@Table(name = "SQA_REWARD")
public class Reward extends AbstractVersionedEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long rewardId;

    protected Integer amount;

    protected Date date;

    protected String description;

    @ManyToOne(fetch=FetchType.EAGER)
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    private Project project;


    /**
     * Get Reward Idw
     *
     * @return Long
     */
    public Long getRewardId() {
        return rewardId;
    }


    /**
     * Set Reward Id
     *
     * @param Long rewardId
     * @return Reward
     */
    public Reward setRewardId(Long rewardId) {
        this.rewardId = rewardId;

        return this;
    }


    /**
     * Get Amount
     *
     * @return Integer
     */
    public Integer getAmount() {
        return amount;
    }


    /**
     * Set Amount
     *
     * @param Integer amount
     * @return Reward
     */
    public Reward setAmount(Integer amount) {
        this.amount = amount;

        return this;
    }


    /**
     * Get Date
     *
     * @return Date
     */
    public Date getDate() {
        return date;
    }


    /**
     * Set Date
     *
     * @param Date date
     * @return Reward
     */
    public Reward setDate(Date date) {
        this.date = date;

        return this;
    }


    /**
     * Get Description
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set Description
     *
     * @param String description
     * @return Reward
     */
    public Reward setDescription(String description) {
        this.description = description;

        return this;
    }


    /**
     * Get User
     *
     * @return User
     */
    public User getUser() {
        return user;
    }


    /**
     * Set User
     *
     * @param User user
     * @return Reward
     */
    public Reward setUser(User user) {
        this.user = user;

        return this;
    }


    /**
     * Get Project
     *
     * @return Project
     */
    public Project getProject() {
        return project;
    }


    /**
     * Set project
     *
     * @param Project project
     * @return Reward
     */
    public Reward setProject(Project project) {
        this.project = project;
        return this;
    }
}
