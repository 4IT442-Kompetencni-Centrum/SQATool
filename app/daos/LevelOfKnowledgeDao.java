package daos;

import models.LevelOfKnowledge;
import java.util.List;

public interface LevelOfKnowledgeDao extends AbstractNonVersionedDao<LevelOfKnowledge> {
	public List<LevelOfKnowledge> getAllLevels();
}
