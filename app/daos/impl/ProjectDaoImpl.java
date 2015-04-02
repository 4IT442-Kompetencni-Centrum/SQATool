package daos.impl;

import java.util.ArrayList;
import java.util.List;

import models.Project;
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
}
