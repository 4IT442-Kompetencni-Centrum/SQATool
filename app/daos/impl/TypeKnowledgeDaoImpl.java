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
	public List<TypeKnowledge> findAll() {
		TypedQuery<TypeKnowledge> query = JPA.em().createQuery("SELECT kn FROM TypeKnowledge as kn", TypeKnowledge.class) ;
		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			Logger.debug("No result was found for Knowledge");
			return null;
		}
	}
}
