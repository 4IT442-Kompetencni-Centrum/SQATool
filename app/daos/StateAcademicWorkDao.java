package daos;

import models.StateAcademicWork;

import java.util.HashMap;

public interface StateAcademicWorkDao extends AbstractNonVersionedDao<StateAcademicWork>{
	public StateAcademicWork findByKey(String key);

	/**
	 * Returns hash map for select box
	 *
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> getOptions();
}
