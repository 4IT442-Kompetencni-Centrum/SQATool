package daos.impl;

import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import models.StateProject;
import play.db.jpa.JPA;
import daos.StateProjectDao;

public class StateProjectDaoImpl extends AbstractNonVersionedDaoImpl<StateProject> implements StateProjectDao{

	@Override
	public StateProject findByKey(@NotNull String key) {
		TypedQuery<StateProject> q = JPA.em().createQuery("SELECT sp FROM StateProject sp where sp.key = :key", StateProject.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

}
