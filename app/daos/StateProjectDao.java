package daos;

import models.StateProject;

public interface StateProjectDao extends AbstractNonVersionedDao<StateProject>{
	public StateProject findByKey(String key);
}
