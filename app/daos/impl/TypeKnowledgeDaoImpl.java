package daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
		TypedQuery query = JPA.em().createQuery("SELECT kn FROM TypeKnowledge", TypeKnowledge.class) ;
		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			Logger.debug("No result was found for Knowledge");
			return null;
		}
	}

}
