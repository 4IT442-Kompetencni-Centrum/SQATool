package daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.cfg.NotYetImplementedException;

import play.Logger;
import play.db.jpa.JPA;
import models.LevelOfKnowledge;
import models.TypeKnowledge;
import models.UsersKnowledge;
import daos.LevelOfKnowledgeDao;
import daos.TypeKnowledgeDao;

import java.util.List;

public class LevelOfKnowledgeDaoImpl extends AbstractNonVersionedDaoImpl<LevelOfKnowledge>
		implements LevelOfKnowledgeDao {

	@Override
	public List<LevelOfKnowledge> getAllLevels() {
		TypedQuery<LevelOfKnowledge> query = JPA.em().createQuery("SELECT lk FROM LevelOfKnowledge as lk", LevelOfKnowledge.class) ;
		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			Logger.debug("No result was found for Knowledge");
			return null;
		}
	}

}
