package daos.impl;

import daos.ProjectDao;
import models.Project;

/**
 * Implementation of DAO for DB operations for Project objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class ProjectDaoImpl extends AbstractDaoImpl<Project> implements ProjectDao{
	/**
	 * Package visible constructor
	 */
	ProjectDaoImpl() {
		/*
		 *Because of this, no controllers can create new instances of DAO. 
		 */
	}
}
