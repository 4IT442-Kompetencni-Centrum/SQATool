package daos;

import models.AcademicWork;
import daos.impl.AbstractVersionedDaoImpl;

import java.util.Collection;
import java.util.List;

public interface AcademicWorkDao extends AbstractVersionedDao<AcademicWork> {
	public List<AcademicWork> getUsersAcademicWork(Long userId);

}
