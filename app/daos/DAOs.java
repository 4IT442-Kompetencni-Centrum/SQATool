package daos;

/**
 * Storage class for DAOs.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class DAOs {
	private static ProjectDao projectDao;

	public static ProjectDao getProjectDao() {
		if (projectDao == null) {
			projectDao = new ProjectDaoImpl();
		}
		return projectDao;
	}
	
	
	
}
