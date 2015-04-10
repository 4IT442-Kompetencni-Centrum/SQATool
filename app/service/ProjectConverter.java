package service;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import daos.impl.DAOs;
import models.Partner;
import models.Project;
import views.data.ProjectDto;

public class ProjectConverter {
	public static Project convertToEntity(ProjectDto orig) {
		Project res = new Project();
		res.setProjectId(orig.getProjectId());
		res.setName(orig.getName());
		res.setShortcut(orig.getShortcut());
		res.setDateStart(orig.getDateStart());
		res.setDateEnd(orig.getDateEnd());
		res.setDescription(orig.getDescription());
		res.setLaboriousnessGues(orig.getLaboriousnessGues());
		res.setLaboriousnessReal(orig.getLaboriousnessReal());
		if (orig.getPartnerIds() != null) {
			List<Partner> partners = new ArrayList<Partner>();
			for(Long partnerId : orig.getPartnerIds()) {
				Partner partner = DAOs.getPartnerDao().findById(partnerId);
				if (partner == null) {
					Logger.error("Partner with ID {} was not found." + partnerId);
				} else {
					partners.add(partner);
				}
			}
			res.setPartners(partners);
		}
		return res;
	}
	
	public static ProjectDto convertToDto(Project orig) {
		ProjectDto res = new ProjectDto();
		res.setProjectId(orig.getProjectId());
		res.setName(orig.getName());
		res.setDateStart(orig.getDateStart());
		res.setDateEnd(orig.getDateEnd());
		res.setDescription(orig.getDescription());
		res.setLaboriousnessGues(orig.getLaboriousnessGues());
		res.setLaboriousnessReal(orig.getLaboriousnessReal());
		res.setShortcut(orig.getShortcut());
		return res;		
	}
	
	public static List<ProjectDto> convertListToDto(List<Project> orig){
		List<ProjectDto> res = new ArrayList<ProjectDto>();
		
		for (Project p : orig) {
			res.add(convertToDto(p));
		}
		
		return res;
	}
}
