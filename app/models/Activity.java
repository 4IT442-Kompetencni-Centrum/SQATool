package models;

import javax.persistence.*;

import play.data.validation.Constraints;

import java.sql.Time;
import java.util.Date;


/**
 * Activity Entity
 *
 * @author Aleš Jiránek <a.jiranek@centrum.cz>
 */
@Entity
@Table(name = "SQA_ACTIVITY")
public class Activity extends AbstractVersionedEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long activityId;

    @Constraints.Required
    protected String name;

    protected Date date;

    protected Time time;

    protected String place;

    protected String description;

    @ManyToOne(fetch = FetchType.EAGER)
    protected TypeActivity typeActivity;


    /**
     * Get Activity Id
     *
     * @return Long
     */
    public Long getActivityId() {
        return activityId;
    }


    /**
     * Set Activity Id
     *
     * @param Long activityId
     * @return Activity
     */
    public Activity setActivityId(Long activityId) {
        this.activityId = activityId;

        return this;
    }


    /**
     * Get Activity Name
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Set Activity Name
     *
     * @param String name
     * @return Activity
     */
    public Activity setName(String name) {
        this.name = name;

        return this;
    }

    /**
     * Get Activity date
     *
     * @return Date
     */
    public Date getDate() {
        return date;
    }


    /**
     * Set Activity Date
     *
     * @param Date date
     * @return Activity
     */
    public Activity setDate(Date date) {
        this.date = date;

        return this;
    }


    /**
     * Get Activity Time
     *
     * @return Time
     */
    public Time getTime() {
        return time;
    }


    /**
     * Set Activity Time
     *
     * @param Time time
     * @return Activity
     */
    public Activity setTime(Time time) {
        this.time = time;

        return this;
    }


    /**
     * Get Activity Place
     *
     * @return String
     */
    public String getPlace() {
        return place;
    }


    /**
     * Set Activity Place
     *
     * @param String place
     * @return Activity
     */
    public Activity setPlace(String place) {
        this.place = place;

        return this;
    }


    /**
     * Get Activity Description
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set Activity Description
     *
     * @param String description
     * @return Activity
     */
    public Activity setDescription(String description) {
        this.description = description;

        return this;
    }


    /**
     * Get Type Activity
     *
     * @return TypeActivity
     */
    public TypeActivity getTypeActivity() {
        return typeActivity;
    }


    /**
     * Set Type Activity
     *
     * @param TypeActivity typeActivity
     * @return Activity
     */
    public Activity setTypeActivity(TypeActivity typeActivity) {
        this.typeActivity = typeActivity;

        return this;
    }
}
