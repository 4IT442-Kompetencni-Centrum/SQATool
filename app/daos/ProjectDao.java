package daos;

import java.util.List;

import models.Project;

/**
 * DAO for DB operations for Project objects.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public interface ProjectDao extends AbstractVersionedDao<Project> {
	/**
	 * Method returns list of project where user with given id participates
	 * @param userId
	 * @return
	 */
	public List<Project> getAllProjectsForUser(Long userId);
	
}
