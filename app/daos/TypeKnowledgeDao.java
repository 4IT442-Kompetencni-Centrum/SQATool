package daos;

import models.TypeKnowledge;
import java.util.List;

public interface TypeKnowledgeDao extends AbstractNonVersionedDao<TypeKnowledge> {
	public List<TypeKnowledge> getUsersNotFiledRequiredKnowledge(Long userId);
	public List<TypeKnowledge> getAllKnowledge();
}
