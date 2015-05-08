package daos;

import models.LevelOfKnowledge;
import models.TypeActivity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LevelOfKnowledgeDao extends AbstractNonVersionedDao<LevelOfKnowledge> {

	/**
	 * Returns all level of knowledges
	 * @return
	 */
	public List<LevelOfKnowledge> findAll();
}
