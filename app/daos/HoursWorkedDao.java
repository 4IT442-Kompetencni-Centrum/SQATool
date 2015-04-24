package daos;

import java.util.List;

import models.HoursWorked;
import models.Project;
import models.User;

/**
 * DAO for DB operations for HoursWorked objects.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public interface HoursWorkedDao extends AbstractVersionedDao<HoursWorked>{
	public List<HoursWorked> getAllForProject(Project project);
	public List<HoursWorked> getAllForProjectAndUser(Project project, User user);
}
