package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_STATE_ACADEMIC_WORK")
public class StateAcademicWork extends AbstractEnumWithKey{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long stateAcademicWorkId;

    public Long getStateAcademicWorkId(){
        return stateAcademicWorkId;
    }

    public StateAcademicWork setStateAcademicWorkId(Long stateAcademicWorkId){
        this.stateAcademicWorkId = stateAcademicWorkId;
        return this;
    }
}
