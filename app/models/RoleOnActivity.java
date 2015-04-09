package models;

import play.data.validation.Constraints;

import javax.persistence.*;


@Entity
@Table(name = "SQA_ROLE_ON_ACTIVITY")
public class RoleOnActivity extends AbstractVersionedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long roleOnActivity;

    @Constraints.Required
    protected String name;


    /**
     * Get Type Activity Id
     *
     * @return Long
     */
    public Long getRoleOnActivityId() {
        return roleOnActivity;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long typeActivityId
     * @return RoleOnActivity
     */
    public RoleOnActivity setRoleOnActivityId(Long typeActivityId) {
        this.roleOnActivity = typeActivityId;

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
     * @return RoleOnActivity
     */
    public RoleOnActivity setName(String name) {
        this.name = name;

        return this;
    }
}
