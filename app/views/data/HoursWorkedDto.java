package views.data;

import java.util.Date;

import play.data.format.Formats;

public class HoursWorkedDto {
	private Long hoursWorkedId;
	private Float numberOfHours;
	private String description;
	private UserDto user;
	private Long projectId;
	private String stateHoursWorkedKey;
	@Formats.DateTime(pattern="dd.MM.yyyy")
	private Date timeFrom;
	@Formats.DateTime(pattern="dd.MM.yyyy")
	private Date timeTo;
	
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
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getStateHoursWorkedKey() {
		return stateHoursWorkedKey;
	}
	public void setStateHoursWorkedKey(String stateHoursWorkedKey) {
		this.stateHoursWorkedKey = stateHoursWorkedKey;
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
