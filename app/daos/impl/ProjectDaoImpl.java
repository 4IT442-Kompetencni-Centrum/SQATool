package daos.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Project;
import models.User;
import play.Logger;
import play.db.jpa.JPA;
import service.Configuration;
import daos.ProjectDao;

/**
 * Implementation of DAO for DB operations for Project objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class ProjectDaoImpl extends AbstractVersionedDaoImpl<Project> implements ProjectDao{
	/**
	 * Package visible constructor
	 */
	ProjectDaoImpl() {
		/*
		 *Because of this, no controllers can create new instances of DAO. 
		 */
	}

	@Override
	public List<Project> getAllProjectsForUser(Long userId) {

		Query query = JPA.em().createQuery("SELECT p FROM Project p WHERE p.visible = TRUE AND p.userOnProject.user.id = :user ORDER BY p.dateStart");
		query.setParameter("user", userId);
		return query.getResultList();
	}

	@Override
	public List<Project> getAllProject(Integer start, Integer limit) {
		if (start == null) {
			start = 0;
		}
		if (limit == null) {
			limit = Configuration.PAGE_SIZE;
			Logger.debug("No limit was given to getAllProject. {} is now set as limit.", Configuration.PAGE_SIZE);
		}
		Query query = JPA.em().createQuery("SELECT p FROM Project p WHERE p.visible = TRUE ORDER BY p.dateStart").setMaxResults(limit).setFirstResult(start);
		return query.getResultList();
	}

	@Override
	public Integer getNumberOfProjects() {
		TypedQuery<Long> q = JPA.em().createQuery("SELECT count(p) FROM Project p WHERE p.visible = TRUE", Long.class);
		return q.getSingleResult().intValue();
	}
	
	@Override
	public Integer getNumberOfProjectsForUser(User user) {
		TypedQuery<Long> q = JPA.em().createQuery("SELECT count(p) FROM Project p JOIN p.userOnProject uop WHERE p.visible = TRUE AND uop.user = :user", Long.class);
		q.setParameter("user", user);
		return q.getSingleResult().intValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getProjectsForUser(User user, Integer start,
			Integer limit) {
		if (start == null) {
			start = 0;
		}
		if (limit == null) {
			limit = Configuration.PAGE_SIZE;
			Logger.debug("No limit was given to getAllProjectsForUser. {} is now set as limit.", Configuration.PAGE_SIZE);
		}
		Query query = JPA.em().createQuery("SELECT p, SUM(hw.numberOfHours) FROM Project p "
										 + "JOIN p.userOnProject uop LEFT OUTER JOIN p.hoursWorked hw "
										 + "WHERE p.visible = TRUE AND uop.user = :user AND (hw.user = uop.user OR hw = null) "
										 + "GROUP BY p ORDER BY p.dateStart "
//										 + "WHERE pn.visible = TRUE AND uopn.user = :user2) "
										 + " ")
										 .setMaxResults(limit).setFirstResult(start);
		query.setParameter("user", user);
		//query.setParameter("user2", user);
		return query.getResultList();
	}
}
