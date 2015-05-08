package daos.impl;

import daos.KnowledgeDao;
import daos.TypeKnowledgeDao;
import models.Knowledge;
import models.TypeKnowledge;
import org.hibernate.cfg.NotYetImplementedException;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

public class KnowledgeDaoImpl extends AbstractNonVersionedDaoImpl<Knowledge>
		implements KnowledgeDao {


}
