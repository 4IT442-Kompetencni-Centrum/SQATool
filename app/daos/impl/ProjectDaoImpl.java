package daos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Project;
import play.Logger;
import play.db.jpa.JPA;
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
		//TODO tmichalicka
		return new ArrayList<Project>();
	}

	@Override
	public List<Project> getAllProject(Integer start, Integer limit) {
		if (start == null) {
			start = 0;
		}
		if (limit == null) {
			limit = 30;
			Logger.debug("No limit was given to getAllProject. 30 is now set as limit.");
		}
		Query query = JPA.em().createQuery("SELECT p FROM Project p WHERE p.visible = TRUE ORDER BY p.dateStart DESC").setMaxResults(limit).setFirstResult(start);
		return query.getResultList();
	}

	@Override
	public Integer getNumberOfProjects() {
		TypedQuery<Long> q = JPA.em().createQuery("SELECT count(p) FROM Project p WHERE p.visible = TRUE", Long.class);
		return q.getSingleResult().intValue();
	}
}
