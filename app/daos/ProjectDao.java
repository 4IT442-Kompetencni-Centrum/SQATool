package daos;

import java.util.List;

import models.Project;
import models.User;

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
	
	/**
	 * Method returns list of project where user with given id participates
	 * @param userId
	 * @return
	 */
	public List<Object[]> getProjectsForUser(User user, Integer start, Integer limit);
	
	/**
	 * Method returns list of all projects
	 * @param start - index of first project
	 * @param limit - maximum number of projects
	 * @return
	 */
	public List<Project> getAllProject(Integer start, Integer limit);
	
	/**
	 * Method returns total number of all projects
	 * @return
	 */
	public Integer getNumberOfProjects();
	
	/**
	 * Method returns total number of projects where user is assigned
	 * @return
	 */
	public Integer getNumberOfProjectsForUser(User user);
	
	
}
