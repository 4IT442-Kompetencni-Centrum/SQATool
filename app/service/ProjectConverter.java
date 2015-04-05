package service;

import models.Project;
import views.data.ProjectDto;

public class ProjectConverter {
	public static Project convertToEntity(ProjectDto orig) {
		Project res = new Project();
		res.setProjectId(orig.getProjectId());
		res.setName(orig.getName());
		res.setDateStart(orig.getDateStart());
		res.setDateEnd(orig.getDateEnd());
		res.setDescription(orig.getDescription());
		res.setLaboriousnessGues(orig.getLaboriousnessGues());
		res.setLaboriousnessReal(orig.getLaboriousnessReal());
		return res;
	}
}
