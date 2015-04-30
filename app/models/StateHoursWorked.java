package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SQA_STATE_HOURS_WORKED")
public class StateHoursWorked extends AbstractEnumWithKey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long stateHoursWorkedId;

	public Long getStateHoursWorkedId() {
		return stateHoursWorkedId;
	}

	public void setStateHoursWorkedId(Long stateHoursWorkedId) {
		this.stateHoursWorkedId = stateHoursWorkedId;
	}
	
	
}
