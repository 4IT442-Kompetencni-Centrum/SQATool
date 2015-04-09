package daos;

import models.StateActivity;

public interface StateActivityDao extends AbstractNonVersionedDao<StateActivity>{
	public StateActivity findByKey(String key);
}
