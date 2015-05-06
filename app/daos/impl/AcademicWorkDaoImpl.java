package daos.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import play.Logger;
import play.db.jpa.JPA;
import models.AcademicWork;
import models.UsersKnowledge;
import daos.AcademicWorkDao;

public class AcademicWorkDaoImpl extends AbstractVersionedDaoImpl<AcademicWork>
implements AcademicWorkDao {
	
	public AcademicWorkDaoImpl(){}

	@Override
	public List<AcademicWork> getUsersAcademicWork(Long userId) {
		TypedQuery<AcademicWork> query = JPA.em().createQuery("SELECT aw FROM AcademicWork aw join aw.user user WHERE user.id = :userId ", AcademicWork.class);
		query.setParameter("userId", userId);
		try {
			return query.getResultList();
		} catch(NoResultException ex){
			Logger.debug("No result found for userId {}", userId);
			return null;
		}
	}


}
