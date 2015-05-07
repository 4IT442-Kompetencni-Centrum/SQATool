package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_LEVEL_OF_KNOWLEDGE")
public class LevelOfKnowledge extends AbstractEnumWithKey{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long levelOfKnowledgeId;

    /**
     * Get Level Of Knowledge Id
     *
     * @return Long
     */
    public Long getLevelOfKnowledgeId() {
        return levelOfKnowledgeId;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long levelOfKnowledgeId
     * @return LevelOfKnowledge
     */
    public LevelOfKnowledge setLevelOfKnowledgeId(Long levelOfKnowledgeId) {
        this.levelOfKnowledgeId = levelOfKnowledgeId;

        return this;
    }
}
