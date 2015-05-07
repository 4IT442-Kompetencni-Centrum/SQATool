package daos.impl;

import daos.StateAcademicWorkDao;
import daos.StateActivityDao;
import models.StateAcademicWork;
import models.StateActivity;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

public class StateAcademicWorkDaoImpl extends AbstractNonVersionedDaoImpl<StateAcademicWork> implements StateAcademicWorkDao {

	@Override
	public StateAcademicWork findByKey(@NotNull String key) {
		TypedQuery<StateAcademicWork> q = JPA.em().createQuery("SELECT saw FROM StateAcademicWork saw where saw.key = :key", StateAcademicWork.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

	@Override
	public HashMap<String, String> getOptions() {
		HashMap<String,String> options = new HashMap<>();

		Query query = JPA.em().createQuery("SELECT saw FROM StateAcademicWork saw ORDER BY saw.key");
		List<StateAcademicWork> states = query.getResultList();

		for(StateAcademicWork stateAcademicWork : states) {
			options.put(stateAcademicWork.getKey(),stateAcademicWork.getValue());
		}

		return options;
	}

}
