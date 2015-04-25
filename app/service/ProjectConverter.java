package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Partner;
import models.Project;
import models.TypeUserOnProject;
import models.User;
import models.UserOnProject;
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
		res.setUserOnProject(new ArrayList<UserOnProject>());
		if (orig.getManagerId() != null) {
			UserOnProject userOnProject = DAOs.getUserOnProjectDao().getByProjectAndUser(orig.getManagerId(), res.getProjectId());
			if (userOnProject == null) {
				userOnProject = new UserOnProject();
				userOnProject.setProject(res);
				userOnProject.setUser(DAOs.getUserDao().findById(orig.getManagerId()));
				userOnProject.setVisible(true);
				userOnProject.setTypeUserOnProject(DAOs.getTypeUserOnProject().findByKey(EnumerationWithKeys.PROJECT_MANAGER_KEY));
			}
			res.getUserOnProject().add(userOnProject);
		}
		if (orig.getMemberIds() != null) {
			TypeUserOnProject memberEnum = DAOs.getTypeUserOnProject().findByKey(EnumerationWithKeys.PROJECT_MEMBER_KEY);
			for (Long userId : orig.getMemberIds()) {
				if (userId == null) continue;
				UserOnProject userOnProject = null;
					if (res.getProjectId() != null) {
						userOnProject = DAOs.getUserOnProjectDao().getByProjectAndUser(userId, res.getProjectId());
						Logger.debug("Searching UserOnProject for userId {} and projectId {} ", userId, res.getProjectId());
					}
				if (userOnProject == null) {
					userOnProject = new UserOnProject();
					userOnProject.setProject(res);
					userOnProject.setUser(DAOs.getUserDao().findById(userId));
					userOnProject.setVisible(true);
					userOnProject.setTypeUserOnProject(memberEnum);
				}
				res.getUserOnProject().add(userOnProject);
			}
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
		if (orig.getUserOnProject() != null) {
			res.setMemberIds(new ArrayList<Long>());
			res.setMemberNames(new ArrayList<String>());
			for (UserOnProject uop : orig.getUserOnProject()) {
				User u = uop.getUser();
				if (EnumerationWithKeys.PROJECT_MANAGER_KEY.equals(uop.getTypeUserOnProject().getKey())) {
					if (u != null) {
						res.setManagerId(u.getId());
						res.setManagerName(u.getFirstName()+" "+u.getLastName());
					}
				} else {
					if (u != null) {
						res.getMemberIds().add(u.getId());
						res.getMemberNames().add(u.getFirstName()+" "+u.getLastName());
					}
				}
			}
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
