package daos.impl;

import javax.persistence.TypedQuery;

import models.StateHoursWorked;
import play.db.jpa.JPA;
import daos.StateHoursWorkedDao;

public class StateHoursWorkedDaoImpl extends AbstractNonVersionedDaoImpl<StateHoursWorked> implements StateHoursWorkedDao {

	@Override
	public StateHoursWorked findByKey(String key) {
		TypedQuery<StateHoursWorked> q = JPA.em().createQuery("SELECT shw FROM StateHoursWorked shw where shw.key = :key", StateHoursWorked.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

}
