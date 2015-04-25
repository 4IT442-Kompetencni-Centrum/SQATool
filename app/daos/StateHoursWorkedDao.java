package daos;

import models.StateHoursWorked;

public interface StateHoursWorkedDao extends AbstractNonVersionedDao<StateHoursWorked>{
	public StateHoursWorked findByKey(String key);
}
