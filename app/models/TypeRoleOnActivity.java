package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_TYPE_ROLE_ON_ACTIVITY")
public class TypeRoleOnActivity extends AbstractEnumWithKey{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long typeRoleOnActivityId;


    /**
     * Get Type Activity Id
     *
     * @return Long
     */
    public Long getTypeRoleOnActivityId() {
        return typeRoleOnActivityId;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long typeRoleOnActivityId
     * @return TypeRoleOnActivity
     */
    public TypeRoleOnActivity setTypeRoleOnActivityId(Long typeRoleOnActivityId) {
        this.typeRoleOnActivityId = typeRoleOnActivityId;

        return this;
    }
}
