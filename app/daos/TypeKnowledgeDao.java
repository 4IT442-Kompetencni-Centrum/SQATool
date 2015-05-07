package daos;

import models.TypeKnowledge;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TypeKnowledgeDao extends AbstractNonVersionedDao<TypeKnowledge> {
	public List<TypeKnowledge> getUsersNotFiledRequiredKnowledge(Long userId);
	public List<TypeKnowledge> getAllKnowledge();
	public TypeKnowledge findByKey(@NotNull String key);
}
