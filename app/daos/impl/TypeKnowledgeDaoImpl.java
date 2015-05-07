package daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.cfg.NotYetImplementedException;

import play.Logger;
import play.db.jpa.JPA;
import models.TypeKnowledge;
import models.UsersKnowledge;
import daos.TypeKnowledgeDao;

import java.util.List;

public class TypeKnowledgeDaoImpl extends AbstractNonVersionedDaoImpl<TypeKnowledge>
		implements TypeKnowledgeDao {


	@Override
	public List<TypeKnowledge> getUsersNotFiledRequiredKnowledge(Long userId) {
		throw new NotYetImplementedException();
	}

	@Override
	public List<TypeKnowledge> getAllKnowledge() {
		TypedQuery<TypeKnowledge> query = JPA.em().createQuery("SELECT kn FROM TypeKnowledge as kn", TypeKnowledge.class) ;
		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			Logger.debug("No result was found for Knowledge");
			return null;
		}
	}

	public TypeKnowledge findByKey(@NotNull String key) {
		TypedQuery<TypeKnowledge> q = JPA.em().createQuery("SELECT k FROM TypeKnowledge k where k.key = :key", TypeKnowledge.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

}
