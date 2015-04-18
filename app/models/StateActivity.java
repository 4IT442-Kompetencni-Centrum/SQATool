package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_STATE_ACTIVITY")
public class StateActivity extends AbstractEnumWithKey{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long stateActivityId;

    /**
     * Get Type Activity Id
     *
     * @return Long
     */
    public Long getStateActivityId() {
        return stateActivityId;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long typeActivityId
     * @return TypeActivity
     */
    public StateActivity setStateActivityId(Long typeActivityId) {
        this.stateActivityId = typeActivityId;
        return this;
    }
}
