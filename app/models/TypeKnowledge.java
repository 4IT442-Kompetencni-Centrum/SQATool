package models;

import javax.persistence.*;

@Entity
@Table(name="SQA_TYPE_KNOWLEDGE")
public class TypeKnowledge extends AbstractEnumWithoutKey{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long typeKnowledgeId;
	
	@Column(nullable = false)
	private boolean isRequired;
	

	public Long getTypeKnowledgeId() {
		return typeKnowledgeId;
	}

	public void setTypeKnowledgeId(Long typeKnowledgeId) {
		this.typeKnowledgeId = typeKnowledgeId;
	}
	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public TypeKnowledge() {
		super();
	}
	
	
}
