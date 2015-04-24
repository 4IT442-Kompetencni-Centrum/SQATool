package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity HoursWorked
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_HOURS_WORKED")
public class HoursWorked extends AbstractVersionedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hoursWorkedId;
	private Float numberOfHours;
	private String description;
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@ManyToOne
	private StateHoursWorked stateHoursWorked;
	private Date timeFrom;
	private Date timeTo;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getHoursWorkedId() {
		return hoursWorkedId;
	}
	public void setHoursWorkedId(Long hoursWorkedId) {
		this.hoursWorkedId = hoursWorkedId;
	}
	public Float getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(Float numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public StateHoursWorked getStateHoursWorked() {
		return stateHoursWorked;
	}
	public void setStateHoursWorked(StateHoursWorked stateHoursWorked) {
		this.stateHoursWorked = stateHoursWorked;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}
	public Date getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}
	
}
