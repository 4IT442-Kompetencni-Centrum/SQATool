package daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

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

	public LevelOfKnowledge findByKey(@NotNull String key) {
		TypedQuery<LevelOfKnowledge> q = JPA.em().createQuery("SELECT l FROM LevelOfKnowledge l where l.key = :key", LevelOfKnowledge.class);
		q.setParameter("key", key);
		return q.getSingleResult();
	}

	public List<LevelOfKnowledge> findAll() {
		Query query = JPA.em().createQuery("SELECT l FROM LevelOfKnowledge l ORDER BY l.levelOfKnowledgeId");
		return query.getResultList();
	}

}
