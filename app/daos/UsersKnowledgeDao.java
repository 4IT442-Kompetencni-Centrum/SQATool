package daos;

import models.UsersKnowledge;
import models.TypeKnowledge;
import java.util.List;

public interface UsersKnowledgeDao extends AbstractVersionedDao<UsersKnowledge> {
	public List<UsersKnowledge> getUsersKnowledge(Long userId);
	

}
