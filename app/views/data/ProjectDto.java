package views.data;

import java.util.Date;
import java.util.List;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

public class ProjectDto {
    private Long projectId;
    @Required
	private String name;
    @Formats.DateTime(pattern="dd.MM.yyyy")
	private Date dateStart;
    @Formats.DateTime(pattern="dd.MM.yyyy")
	private Date dateEnd;
	@Required
	private String description;
	private Integer laboriousnessGues;
	private Integer laboriousnessReal;
	private List<Long> partnerIds;
	private List<String> partnerNames;
	
	
	public Long getProjectId() {
		return projectId;
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
	public List<Long> getPartnerIds() {
		return partnerIds;
	}
	public void setPartnerIds(List<Long> partnerIds) {
		this.partnerIds = partnerIds;
	}
	@Override
	public String toString() {
		return "ProjectDto [projectId=" + projectId + ", name=" + name
				+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", description=" + description + ", laboriousnessGues="
				+ laboriousnessGues + ", laboriousnessReal="
				+ laboriousnessReal + ", partnerIds=" + partnerIds + "]";
	}
	public List<String> getPartnerNames() {
		return partnerNames;
	}
	public void setPartnerNames(List<String> partnerNames) {
		this.partnerNames = partnerNames;
	}
	
}
