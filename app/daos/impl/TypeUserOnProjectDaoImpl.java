package daos.impl;

import javax.persistence.TypedQuery;

import models.TypeUserOnProject;
import play.db.jpa.JPA;
import daos.TypeUserOnProjectDao;

public class TypeUserOnProjectDaoImpl extends AbstractNonVersionedDaoImpl<TypeUserOnProject> implements TypeUserOnProjectDao{

	TypeUserOnProjectDaoImpl() {
		
	}
	
	@Override
	public TypeUserOnProject findByKey(String key) {
        TypedQuery<TypeUserOnProject> q = JPA.em().createQuery("SELECT tuop FROM TypeUserOnProject tuop where tuop.key = :key", TypeUserOnProject.class);
        q.setParameter("key", key);
        return q.getSingleResult();
	}

}
