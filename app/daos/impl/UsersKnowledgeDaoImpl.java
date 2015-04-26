package daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import play.Logger;
import play.db.jpa.JPA;
import models.TypeKnowledge;
import models.UsersKnowledge;
import daos.UsersKnowledgeDao;

import java.util.List;

public class UsersKnowledgeDaoImpl extends AbstractVersionedDaoImpl<UsersKnowledge>
		implements UsersKnowledgeDao {

	UsersKnowledgeDaoImpl() {
	
	}

	@Override
	public List<UsersKnowledge> getUsersKnowledge(Long userId) {
		TypedQuery<UsersKnowledge> query = JPA.em().createQuery("SELECT uk FROM UsersKnowledge uk WHERE uk.user.id = :userId ", UsersKnowledge.class);
		query.setParameter("userId", userId);
		try {
			return query.getResultList();
		} catch(NoResultException ex){
			Logger.debug("No result found for userId {}", userId);
			return null;
		}
	}

}
