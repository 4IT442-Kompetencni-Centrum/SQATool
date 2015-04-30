package daos.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.UserOnProject;
import play.Logger;
import play.db.jpa.JPA;
import daos.UserOnProjectDao;

/**
 * Implementation of DAO for DB operations for UserOnProject objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class UserOnProjectDaoImpl extends AbstractVersionedDaoImpl<UserOnProject>
		implements UserOnProjectDao {

	UserOnProjectDaoImpl() {
	
	}

	@Override
	public UserOnProject getByProjectAndUser(Long userId, Long projectId) {
		TypedQuery<UserOnProject> query = JPA.em().createQuery("SELECT uop FROM UserOnProject uop WHERE uop.user.id = :userId AND uop.project.projectId = :projectId", UserOnProject.class);
		query.setParameter("projectId", projectId);
		query.setParameter("userId", userId);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			Logger.debug("No result was found for userId {} and projectId {} ", userId, projectId);
			return null;
		}
	}

	@Override
	public List<UserOnProject> getByProject(Long projectId) {
		Query q = JPA.em().createQuery("SELECT uop FROM UserOnProject uop JOIN uop.project p WHERE p.projectId = :projectId");
		q.setParameter("projectId", projectId);
		return q.getResultList();
	}
}
