package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Partner;
import models.Project;
import models.User;
import play.Logger;
import views.data.PartnerDto;
import views.data.ProjectDto;
import daos.impl.DAOs;

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
		res.setVersion(orig.getVersion());
		if (orig.getPartnerIds() != null) {
			Set<Partner> partners = new HashSet<Partner>();
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
	
	public static ProjectDto convertToDto(Project orig, User user) {
		ProjectDto res = new ProjectDto();
		res.setProjectId(orig.getProjectId());
		res.setName(orig.getName());
		res.setDateStart(orig.getDateStart());
		res.setDateEnd(orig.getDateEnd());
		res.setDescription(orig.getDescription());
		res.setLaboriousnessGues(orig.getLaboriousnessGues());
		res.setLaboriousnessReal(orig.getLaboriousnessReal());
		res.setShortcut(orig.getShortcut());
		res.setVersion(orig.getVersion());
		List<String> pNames = new ArrayList<String>();
		List<Long> pIds = new ArrayList<Long>();
		List<PartnerDto> partners = new ArrayList<PartnerDto>();
		for (Partner p : orig.getPartners()) {
			pNames.add(p.getName());
			pIds.add(p.getPartnerId());
			partners.add(PartnerConverter.convertToDto(p));
		}
		res.setPartnerIds(pIds);
		res.setPartnerNames(pNames);
		res.setPartners(partners);
		if (user != null) {
			res.setCanBeDeleted(SecurityService.canDeleteProject(orig, user));
			res.setCanBeModified(SecurityService.canEditProject(orig, user));
		}
		return res;		
	}
	
	public static List<ProjectDto> convertListToDto(List<Project> orig, User user){
		List<ProjectDto> res = new ArrayList<ProjectDto>();
		
		for (Project p : orig) {
			res.add(convertToDto(p, user));
		}
		
		return res;
	}
}
