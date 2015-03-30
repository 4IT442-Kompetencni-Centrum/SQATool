package models;

import play.data.validation.Constraints;

import javax.persistence.*;


@Entity
@Table(name = "SQA_TYPE_ACTIVITY")
public class TypeActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long typeActivityId;

    @Constraints.Required
    protected String name;


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


    /**
     * Get Type Activity Name
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Set Type Activity Name
     *
     * @param String name
     * @return TypeActivity
     */
    public TypeActivity setName(String name) {
        this.name = name;

        return this;
    }
}
