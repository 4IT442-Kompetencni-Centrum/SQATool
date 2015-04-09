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

    @ManyToOne(fetch = FetchType.EAGER)
    protected TypeActivity typeActivity;

    protected Integer capacity;

    protected Integer capacityMax;

    protected Date timeFrom;

    protected Date timeTo;

    protected String place;

    protected String description;

    @ManyToOne(fetch = FetchType.EAGER)
    protected StateActivity stateActivity;


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
     * Get Activity Time From
     *
     * @return Time
     */
    public Date getTimeFrom() {
        return timeFrom;
    }


    /**
     * Set Activity Time
     *
     * @param Date time
     * @return Activity
     */
    public Activity setTimeFrom(Date time) {
        this.timeFrom = time;

        return this;
    }


    /**
     * Get Activity Time To
     *
     * @return Time
     */
    public Date getTimeTo() {
        return timeTo;
    }


    /**
     * Set Activity Time
     *
     * @param Date time
     * @return Activity
     */
    public Activity setTimeTo(Date time) {
        this.timeTo = time;

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


    /**
     * Get Capacity
     *
     * @return Integer
     */
    public Integer getCapacity() {
        return capacity;
    }


    /**
     * Set Capacity
     *
     * @param TypeActivity typeActivity
     * @return Activity
     */
    public Activity setCapacity(Integer capacity) {
        this.capacity = capacity;

        return this;
    }


    /**
     * Get Max Capacity
     *
     * @return Integer
     */
    public Integer getCapacityMax() {
        return capacityMax;
    }


    /**
     * Set Max Capacity
     *
     * @param TypeActivity typeActivity
     * @return Activity
     */
    public Activity setCapacityMax(Integer capacityMax) {
        this.capacityMax = capacityMax;

        return this;
    }


    /**
     * Get State Activity
     *
     * @return StateActivity
     */
    public StateActivity getStateActivity() {
        return stateActivity;
    }


    /**
     * Set stateActivity
     *
     * @param StateActivity stateActivity
     * @return Activity
     */
    public Activity setStateActivity(StateActivity stateActivity) {
        this.stateActivity = stateActivity;

        return this;
    }
}
