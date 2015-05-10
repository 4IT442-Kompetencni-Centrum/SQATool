package models;

import javax.persistence.*;

/**
 * Created by Petr Kadlec on 17.4.
 */
@Entity
@Table(name = "SQA_STATE_USER")
public class StateUser extends AbstractEnumWithKey{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long stateUserId;

    public Long getStateUserId(){
        return stateUserId;
    }

    public StateUser setStateUserId(Long stateUserId){
        this.stateUserId = stateUserId;
        return this;
    }
}
