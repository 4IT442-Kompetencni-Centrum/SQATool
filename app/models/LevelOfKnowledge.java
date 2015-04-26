package models;

import javax.persistence.*;

@Entity
@Table(name="SQA_LEVEL_OF_KNOWLEDGE")
public class LevelOfKnowledge extends AbstractEnumWithoutKey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long typeLevelKnowledgeId;
	
	@Column(nullable = false)
	private Integer numericLevel;
	

	public Integer getNumericLevel() {
		return numericLevel;
	}

	public void setNumericLevel(Integer numericLevel) {
		this.numericLevel = numericLevel;
	}

	public LevelOfKnowledge() {
		super();
	}

	public Long getTypeLevelKnowledgeId() {
		return typeLevelKnowledgeId;
	}

	public void setTypeLevelKnowledgeId(Long typeLevelKnowledgeId) {
		this.typeLevelKnowledgeId = typeLevelKnowledgeId;
	}
}
