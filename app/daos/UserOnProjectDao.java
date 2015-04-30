package daos;

import java.util.List;

import models.UserOnProject;

/**
 * DAO for DB operations for UserOnProject objects.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public interface UserOnProjectDao extends AbstractVersionedDao<UserOnProject> {
	public UserOnProject getByProjectAndUser(Long userId, Long projectId);
	public List<UserOnProject> getByProject(Long projectId);

}
