package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_TYPE_ACTIVITY")
public class TypeActivity extends AbstractEnumWithoutKey{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long typeActivityId;

    /**
     * Get Type Activity Id
     *
     * @return Long
     */
    public Long getTypeActivityId() {
        return typeActivityId;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long typeActivityId
     * @return TypeActivity
     */
    public TypeActivity setTypeActivityId(Long typeActivityId) {
        this.typeActivityId = typeActivityId;

        return this;
    }
}
