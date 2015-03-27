package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Entity Project
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_PROJECT")
public class Project extends AbstractVersionedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private String description;
	private Integer laboriousnessGues;
	private Integer laboriousnessReal;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="project")
	private List<HoursWorked> hoursWorked;
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Partner> partners;
	//TODO tmichalicka projectState
	
	
	public Long getProjectId() {
		return projectId;
	}
	public List<Partner> getPartners() {
		return partners;
	}
	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getLaboriousnessGues() {
		return laboriousnessGues;
	}
	public void setLaboriousnessGues(Integer laboriousnessGues) {
		this.laboriousnessGues = laboriousnessGues;
	}
	public Integer getLaboriousnessReal() {
		return laboriousnessReal;
	}
	public void setLaboriousnessReal(Integer laboriousnessReal) {
		this.laboriousnessReal = laboriousnessReal;
	}
	public List<HoursWorked> getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(List<HoursWorked> hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
