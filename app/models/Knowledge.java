package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Knowledge Entity
 *
 * @author Aleš Jiránek <a.jiranek@centrum.cz>
 */
@Entity
@Table(name = "SQA_KNOWLEDGE")
public class Knowledge extends AbstractVersionedEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long userHasKnowledgeId;

    @ManyToOne(fetch=FetchType.EAGER)
    private TypeKnowledge typeKnowledge;

    @ManyToOne(fetch=FetchType.EAGER)
    private LevelOfKnowledge levelOfKnowledge;


    public Long getUserHasKnowledgeId() {
        return userHasKnowledgeId;
    }

    public Knowledge setUserHasKnowledgeId(Long userHasKnowledgeId) {
        this.userHasKnowledgeId = userHasKnowledgeId;
        return this;
    }

    public TypeKnowledge getTypeKnowledge() {
        return typeKnowledge;
    }

    public Knowledge setTypeKnowledge(TypeKnowledge typeKnowledge) {
        this.typeKnowledge = typeKnowledge;
        return this;
    }

    public LevelOfKnowledge getLevelOfKnowledge() {
        return levelOfKnowledge;
    }

    public Knowledge setLevelOfKnowledge(LevelOfKnowledge levelOfKnowledge) {
        this.levelOfKnowledge = levelOfKnowledge;
        return this;
    }
}
