package forms;


import daos.impl.DAOs;
import models.AcademicWork;
import play.data.validation.Constraints;

import javax.validation.Constraint;
import java.util.List;

public class AcademicWorkForm {

    protected Long academicWorkId;

    @Constraints.Required
    protected String typeAcademicWork;

    @Constraints.Required
    protected String supervisor;

    @Constraints.Required
    protected String stateAcademicWork;

    @Constraints.Required
    @Constraints.MaxLength(value= 200, message="Zadaný název je příliš dlouhý")
    protected String name;

    @Constraints.Required
    protected String description;

    public AcademicWorkForm(){}

    public AcademicWorkForm(AcademicWork academicWork) {
        this.academicWorkId = academicWork.getAcademicWorkId();
        this.typeAcademicWork = academicWork.getTypeAcademicWork().getKey();
        this.supervisor = academicWork.getSupervisor();
        this.stateAcademicWork = academicWork.getStateAcademicWork().getKey();
        this.description = academicWork.getDescription();
        this.name = academicWork.getName();
    }

    public Long getAcademicWorkId() {
        return academicWorkId;
    }

    public AcademicWorkForm setAcademicWorkId(Long academicWorkId) {
        this.academicWorkId = academicWorkId;
        return this;
    }

    public String getTypeAcademicWork() {
        return typeAcademicWork;
    }

    public AcademicWorkForm setTypeAcademicWork(String typeAcademicWork) {
        this.typeAcademicWork = typeAcademicWork;
        return this;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public AcademicWorkForm setSupervisor(String supervisor) {
        this.supervisor = supervisor;
        return this;
    }

    public String getStateAcademicWork() {
        return stateAcademicWork;
    }

    public AcademicWorkForm setStateAcademicWork(String stateAcademicWork) {
        this.stateAcademicWork = stateAcademicWork;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AcademicWorkForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public AcademicWorkForm setName(String name) {
        this.name = name;
        return this;
    }

    public AcademicWork getAcademicWork() {
        AcademicWork academicWork = null;

        if(this.getAcademicWorkId() != null) {
            academicWork = DAOs.getAcademicWorkDao().findById(this.getAcademicWorkId());
        }

        if(academicWork == null) {
            academicWork = new AcademicWork();
        }

        academicWork.setDescription(this.getDescription());
        academicWork.setName(this.getName());
        academicWork.setStateAcademicWork(DAOs.getStateAcademicWorkDao().findByKey(this.getStateAcademicWork()));
        academicWork.setSupervisor(this.getSupervisor());
        academicWork.setTypeAcademicWork(DAOs.getTypeAcademicWorkDao().findByKey(this.getTypeAcademicWork()));

        return academicWork;
    }
}
