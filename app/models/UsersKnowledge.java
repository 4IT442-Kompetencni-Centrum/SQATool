package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SQA_USERS_KNOWLEDGE")
public class UsersKnowledge extends AbstractVersionedEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usersKnowledgeId;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@ManyToOne(fetch=FetchType.EAGER)
	private TypeKnowledge knowledge;
	@ManyToOne(fetch=FetchType.EAGER)
	private LevelOfKnowledge level;

	public Long getUsersKnowledgeId() {
		return usersKnowledgeId;
	}
	public void setUsersKnowledgeId(Long usersKnowledgeId) {
		this.usersKnowledgeId = usersKnowledgeId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TypeKnowledge getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(TypeKnowledge knowledge) {
		this.knowledge = knowledge;
	}
	public LevelOfKnowledge getLevel() {
		return level;
	}
	public void setLevel(LevelOfKnowledge level) {
		this.level = level;
	}
	
}
