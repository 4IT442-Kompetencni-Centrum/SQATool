package daos.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Project;
import models.User;
import play.Logger;
import play.db.jpa.JPA;
import service.Configuration;
import service.EnumerationWithKeys;
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

		Query query = JPA.em().createQuery("SELECT p FROM Project p JOIN p.userOnProject uop WHERE p.visible = TRUE AND uop.user.id = :user ORDER BY p.dateStart");
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
		if (JPA.em() == null) {
			Logger.error("EM je null");
		}
		Query query = JPA.em().createNativeQuery("SELECT p.*, sum(hw.numberOfHours) as numOfHours FROM SQA_PROJECT p "
										 + " JOIN SQA_USER_ON_PROJECT uop ON p.projectid = uop.project_projectid AND (uop.user_id = :userid) "
										 + " JOIN SQA_HOURS_WORKED hw ON p.projectid = hw.project_projectid "
										 + " WHERE p.visible = TRUE AND (hw.user_id = uop.user_id OR uop.typeUserOnProject_typeUserOnProjectId = :projectManager) "
										 + " GROUP BY p.projectid "
										 + " UNION "
										 + " SELECT p.* , 0.0 as numOfHours FROM SQA_PROJECT p "
										 + " WHERE p.projectid IN (SELECT uop.project_projectid FROM SQA_USER_ON_PROJECT uop "
										 + " LEFT OUTER JOIN SQA_HOURS_WORKED hw ON hw.project_projectid = uop.project_projectid AND (hw.user_id = :userid2 OR uop.typeUserOnProject_typeUserOnProjectId = :projectManager2) "
										 + " WHERE uop.user_id = :userid2 AND hw IS NULL "
										 + " ) "
										 + " ", Project.PROJECT_WORKED_HOURS_MAPPING)
										 .setMaxResults(limit).setFirstResult(start);
		query.setParameter("userid", user.getId());
		query.setParameter("userid2", user.getId());
		Long pmId = DAOs.getTypeUserOnProject().findByKey(EnumerationWithKeys.PROJECT_MANAGER_KEY).getTypeUserOnProjectId();
		query.setParameter("projectManager", pmId);
		query.setParameter("projectManager2", pmId);
		return query.getResultList();
	}

	@Override
	public Project getProjectByShortcut(String str) {
		TypedQuery<Project> q = JPA.em().createQuery("SELECT p FROM Project p WHERE p.visible = TRUE AND p.shortcut = :shortcut", Project.class);
		q.setParameter("shortcut", str);
		try {
			List<Project> res = q.getResultList();
			if (res == null || res.size() == 0) return null;
			return res.get(0);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Double getRealLaboriousness(Project project) {
		TypedQuery<Double> q = JPA.em().createQuery("SELECT SUM(hw.numberOfHours) from HoursWorked hw WHERE hw.project = :project", Double.class);
		q.setParameter("project", project);
		return q.getSingleResult();
	}
}
