package daos.impl;

import java.util.List;

import javax.persistence.Query;

import play.db.jpa.JPA;
import models.HoursWorked;
import models.Project;
import models.User;
import daos.HoursWorkedDao;

/**
 * Implementation of DAO for DB operations for HoursWorked objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class HoursWorkedDaoImpl extends AbstractVersionedDaoImpl<HoursWorked> implements
		HoursWorkedDao {

	HoursWorkedDaoImpl() {

	}

	@Override
	public List<HoursWorked> getAllForProject(Project project) {
		Query query = JPA.em().createQuery("SELECT hw FROM HoursWorked hw WHERE hw.project = :project");
		query.setParameter("project", project);
		return query.getResultList();
	}

	@Override
	public List<HoursWorked> getAllForProjectAndUser(Project project, User user) {
		Query query = JPA.em().createQuery("SELECT hw FROM HoursWorked hw WHERE hw.user = :user AND hw.project = :project");
		query.setParameter("user", user);
		query.setParameter("project", project);
		return query.getResultList();
	}

}
