package daos;

import models.TypeKnowledge;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TypeKnowledgeDao extends AbstractNonVersionedDao<TypeKnowledge> {
	public List<TypeKnowledge> findAll();
}
