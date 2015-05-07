package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;


/**
 * Activity Entity
 *
 * @author Aleš Jiránek <a.jiranek@centrum.cz>
 */
@Entity
@Table(name = "SQA_ACADEMIC_WORK")
public class AcademicWork extends AbstractVersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long academicWorkId;

    @ManyToOne(fetch=FetchType.EAGER)
    protected TypeAcademicWork typeAcademicWork;

    protected String name;

    protected String description;

    protected String supervisor;

    @ManyToOne(fetch=FetchType.EAGER)
    protected StateAcademicWork stateAcademicWork;

    public Long getAcademicWorkId() {
        return academicWorkId;
    }

    public AcademicWork setAcademicWorkId(Long academicWorkId) {
        this.academicWorkId = academicWorkId;
        return this;
    }

    public TypeAcademicWork getTypeAcademicWork() {
        return typeAcademicWork;
    }

    public AcademicWork setTypeAcademicWork(TypeAcademicWork typeAcademicWork) {
        this.typeAcademicWork = typeAcademicWork;
        return this;
    }

    public String getName() {
        return name;
    }

    public AcademicWork setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AcademicWork setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public AcademicWork setSupervisor(String supervisor) {
        this.supervisor = supervisor;
        return this;
    }

    public StateAcademicWork getStateAcademicWork() {
        return stateAcademicWork;
    }

    public AcademicWork setStateAcademicWork(StateAcademicWork stateAcademicWork) {
        this.stateAcademicWork = stateAcademicWork;
        return this;
    }
}
