package daos.impl;

import daos.StateActivityDao;
import models.StateActivity;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

public class StateActivityDaoImpl extends AbstractNonVersionedDaoImpl<StateActivity> implements StateActivityDao {

	@Override
	public StateActivity findByKey(@NotNull String key) {
		TypedQuery<StateActivity> q = JPA.em().createQuery("SELECT sa FROM StateActivity sa where sa.key = :key", StateActivity.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

}
