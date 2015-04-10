package forms;

import daos.impl.DAOs;
import models.Activity;
import models.StateActivity;
import models.TypeActivity;
import play.data.format.Formats;
import play.data.validation.Constraints;
import service.EnumerationWithKeys;

import java.util.Date;


/**
 * Activity Entity
 *
 * @author Aleš Jiránek <a.jiranek@centrum.cz>
 */
public class ActivityForm {

    protected Long activityId;

    @Constraints.Required
    protected String name;

    @Constraints.Required
    protected String typeActivity;

    protected Integer capacityMax;

    @Formats.DateTime(pattern="dd.MM.yyyy HH:mm")
    protected Date timeFrom;

    @Formats.DateTime(pattern="dd.MM.yyyy HH:mm")
    protected Date timeTo;

    protected String place;

    protected String description;


    public ActivityForm() {
    }

    public ActivityForm(Activity activity) {
        this.activityId = activity.getActivityId();
        this.name = activity.getName();
        this.typeActivity = activity.getTypeActivity().getKey();
        this.capacityMax = activity.getCapacityMax();
        this.timeFrom = activity.getTimeFrom();
        this.timeTo = activity.getTimeTo();
        this.place = activity.getPlace();
        this.description = activity.getDescription();
    }


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
    public ActivityForm setActivityId(Long activityId) {
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
     * @return ActivityForm
     */
    public ActivityForm setName(String name) {
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
     * @return ActivityForm
     */
    public ActivityForm setTimeFrom(Date time) {
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
     * @return ActivityForm
     */
    public ActivityForm setTimeTo(Date time) {
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
     * @return ActivityForm
     */
    public ActivityForm setPlace(String place) {
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
     * @return ActivityForm
     */
    public ActivityForm setDescription(String description) {
        this.description = description;

        return this;
    }


    /**
     * Get Type Activity
     *
     * @return TypeActivity
     */
    public String getTypeActivity() {
        return typeActivity;
    }


    /**
     * Set Type Activity
     *
     * @param String typeActivity
     * @return ActivityForm
     */
    public ActivityForm setTypeActivity(String typeActivity) {
        this.typeActivity = typeActivity;

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
     * @param Integer typeActivity
     * @return ActivityForm
     */
    public ActivityForm setCapacityMax(Integer capacityMax) {
        this.capacityMax = capacityMax;

        return this;
    }


    public Activity getActivity() {
        Activity activity = null;

        if (this.getActivityId() != null)
            activity = DAOs.getActivityDao().findById(this.getActivityId());

        if (activity == null)
            activity = new Activity();

        activity.setCapacityMax(this.getCapacityMax());
        activity.setDescription(this.getDescription());
        activity.setName(this.getName());
        activity.setPlace(this.getPlace());
        activity.setTimeFrom(this.getTimeFrom());
        activity.setTimeTo(this.getTimeTo());
        activity.setTypeActivity(DAOs.getTypeActivityDao().findByKey(this.getTypeActivity()));

        if (activity.getActivityId() == null)
            activity.setStateActivity(DAOs.getStateActivityDao().findByKey(EnumerationWithKeys.ACTIVITY_STATE_EMPTY));

        return activity;
    }
}
