package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.OptimisticLockException;

import models.HoursWorked;
import models.Partner;
import models.Project;
import models.User;
import models.UserOnProject;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.ActionsEnum;
import service.Configuration;
import service.EnumerationWithKeys;
import service.HoursWorkedConverter;
import service.ProjectConverter;
import service.SecurityService;
import views.data.HoursWorkedDto;
import views.data.MenuDto;
import views.data.ProjectDto;
import views.html.projects.projectDetail;
import views.html.projects.projectNotFound;
import views.html.projects.projects;
import views.html.projects.projectsEdit;

import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;
/**
 * Controller for actions related to Project
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @see Project
 * @see ProjectDto
 */
@Security.Authenticated(Secured.class)
public class ProjectController extends Controller{

	
	/**
	 * Action shows all projects where user participates
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result showAll(Integer page) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_SHOW_ALL)) {
			return redirect(routes.Application.accessDenied());
		}
		if (page == null) {
			page = 0;
			Logger.debug("No page number is given. Setting 0 (first page).");
		}
		List<Project> proj= DAOs.getProjectDao().getAllProject(page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
		
		Integer totalProjects = DAOs.getProjectDao().getNumberOfProjects();
		Integer numberOfPages = totalProjects % Configuration.PAGE_SIZE == 0 ? totalProjects/Configuration.PAGE_SIZE : totalProjects/Configuration.PAGE_SIZE + 1; 
		Logger.debug("Page with list of projects is shown. Number of projects id db is {}", totalProjects);
		return ok(projects.render(ProjectConverter.convertListToDto(proj, user), getMainMenu(user), numberOfPages, page));
	}
	
	/**
	 * Action shows form for adding new project
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result create() {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_CREATE)) {
			return redirect(routes.Application.accessDenied());
		}
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).fill(new ProjectDto());
		Logger.debug("Page with form for creating new project is shown.");
		return ok(projectsEdit.render(projectForm, getBackToListMenu(user), "Přidat projekt", routes.ProjectController.saveNewProject().absoluteURL(request()), false));
	}
	/**
	 * Action saves new project
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result saveNewProject() {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_CREATE)) {
			return redirect(routes.Application.accessDenied());
		}
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).bindFromRequest();
		Project newProject = ProjectConverter.convertToEntity(projectForm.get());
		newProject.setVisible(true);
		DAOs.getProjectDao().create(newProject);
		if (newProject.getUserOnProject() != null) {
			for (UserOnProject uop : newProject.getUserOnProject()) {
				DAOs.getUserOnProjectDao().create(uop);
			}
		}
		Logger.debug("Action for saving data of new project was called.");
		return redirect(routes.ProjectController.showAll(0).absoluteURL(request()));
	}
	/**
	 * Action shows detail of project
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result detail(Long projectId) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_DETAIL)) {
			return redirect(routes.Application.accessDenied());
		}
		Project project = DAOs.getProjectDao().findById(projectId);
		if (project == null) {
			Logger.info("Partner with id {} was not found, detail can not be shown.", projectId);
			return redirect(routes.ProjectController.projectNotFound(projectId));
		} else {
			Logger.debug("Partner detail page is shown.");
			Boolean isProjectManager = isProjectManager(project, user);
			List<HoursWorkedDto> hoursWorked = null;
			if (isProjectManager != null && isProjectManager == true) {
				//isProjectManager is null for users who doesnt participate on project
				Logger.debug("User is project manager. All timesheets are shown.");
				hoursWorked = HoursWorkedConverter.convertListToDto(DAOs.getHoursWorkedDao().getAllForProject(project));
			} else {
				Logger.debug("User is not project manager. Only his timesheet is shown.");
				hoursWorked = HoursWorkedConverter.convertListToDto(DAOs.getHoursWorkedDao().getAllForProjectAndUser(project, user));
			}
			return ok(projectDetail.render(ProjectConverter.convertToDto(project, null), getBackToListMenu(user), hoursWorked, isProjectManager));
		}
	}
	
	@Transactional(readOnly=false)
	public static Result approveHoursWorked(Long id) {
		Logger.debug("Hours worked with id {} was approved", id);
		HoursWorked hw = DAOs.getHoursWorkedDao().findById(id);
		if (hw != null) {
			hw.setStateHoursWorked(DAOs.getStateHoursWorkedDao().findByKey(EnumerationWithKeys.STATE_HOURS_WORKED_APPROVED));
			DAOs.getHoursWorkedDao().update(hw);
		}
		return ok();
	}
	
	@Transactional(readOnly=false)
	public static Result rejectHoursWorked(Long id) {
		Logger.debug("Hours worked with id {} was rejected", id);
		HoursWorked hw = DAOs.getHoursWorkedDao().findById(id);
		if (hw != null) {
			if (!EnumerationWithKeys.STATE_HOURS_WORKED_APPROVED.equals(hw.getStateHoursWorked().getKey())) {
				hw.setStateHoursWorked(DAOs.getStateHoursWorkedDao().findByKey(EnumerationWithKeys.STATE_HOURS_WORKED_REJECTED));
				DAOs.getHoursWorkedDao().update(hw);
			}
		}
		return ok();
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long projectId) {
		User user = SecurityService.fetchUser(session("authid"));
		ProjectDto dto = ProjectConverter.convertToDto(DAOs.getProjectDao().findById(projectId), user);
		if (dto == null) {
			Logger.info("Project with id {} was not found.", projectId);
			return redirect(routes.ProjectController.projectNotFound(projectId));
		}
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_EDIT) || !dto.isCanBeModified()) {
			return redirect(routes.Application.accessDenied());
		}		
		
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).fill(dto);
		Logger.debug("Page with form for editing project is shown. Edited project has id {} and name {}.", dto.getProjectId(), dto.getName());
		return ok(projectsEdit.render(projectForm, getBackToListMenu(user), "Upravit projekt", routes.ProjectController.updateProject(false).absoluteURL(request()), false));
	}
	
	/**
	 * Action deletes project.
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long projectId) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_DELETE)) {
			return redirect(routes.Application.accessDenied());
		}	
		Project project = DAOs.getProjectDao().findById(projectId);
		if (project != null) {
			DAOs.getProjectDao().delete(project);
		} else {
			Logger.info("Project with id was not found, delete opereation was not successful.", projectId);
			return redirect(routes.ProjectController.projectNotFound(projectId));
		}
		Logger.debug("Delete operation was called to project with id {}.", projectId);
		return redirect(routes.ProjectController.showAll(0).absoluteURL(request()));
	}
	
	/**
	 * Action shows page which informs that project with given id was not found.
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result projectNotFound(Long projectId) {
		Logger.debug("Project not found page is shown. Requested id was {}.", projectId);
		return ok(projectNotFound.render(projectId, getBackToListMenu(SecurityService.fetchUser(session("authid")))));
	}
	
	/**
	 * Action updates project data
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result updateProject(Boolean forceNext) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_EDIT)) {
			return redirect(routes.Application.accessDenied());
		}
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).bindFromRequest();
		Project project = ProjectConverter.convertToEntity(projectForm.get());
		if (forceNext != null && forceNext) {
			Project edited = DAOs.getProjectDao().findById(projectForm.get().getProjectId());
			project.setVersion(edited.getVersion());
			Logger.debug("Force rewrite project data is set. Previous modifications will be deleted.");
		}
		try {
			project.setPartners(new HashSet<Partner>());
			for (Long partnerId : projectForm.get().getPartnerIds()) {
				Partner partner = DAOs.getPartnerDao().findById(partnerId);
				if (partner != null) {
					if (partner.getProjects() != null) {
						partner.setProjects(new HashSet<Project>());
					}
					partner.getProjects().add(project);
					project.getPartners().add(partner);
					Logger.debug("Partner update: {}", partner.toString());
					DAOs.getPartnerDao().update(partner);
				}
			}
			Logger.debug("Updating user on project.");
			if (project.getUserOnProject() != null) {
				Logger.debug("Updating user on project. Number of user on project is {}", project.getUserOnProject().size());
				for (UserOnProject uop : project.getUserOnProject()) {
					DAOs.getUserOnProjectDao().update(uop);
				}
			}
			project.setVisible(true);
			Logger.debug("Project update: {}", project.toString());
			DAOs.getProjectDao().update(project);
		} catch (OptimisticLockException e) {
			Logger.info("Project {} was edited by another user. ", projectForm.get());
			return ok(projectsEdit.render(projectForm, getBackToListMenu(user), "Upravit projekt", routes.ProjectController.updateProject(true).absoluteURL(request()), true));
		}
		Logger.debug("Project update operation was called.");
		return redirect(routes.ProjectController.showAll(0));
	}
	/**
	 * Action saves new HoursWorked
	 * @return
	 */
	@Transactional(readOnly=false)
	public static final Result hoursWorked() {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PROJECT_DETAIL)) {
			return redirect(routes.Application.accessDenied());
		}
		Form<HoursWorkedDto> hoursWorkedForm = Form.form(HoursWorkedDto.class).bindFromRequest();
		HoursWorked hoursWorked = HoursWorkedConverter.convertToEntity(hoursWorkedForm.get());
		hoursWorked.setUser(user);
		hoursWorked.setStateHoursWorked(DAOs.getStateHoursWorkedDao().findByKey(EnumerationWithKeys.STATE_HOURS_WORKED_CREATED));
		DAOs.getHoursWorkedDao().create(hoursWorked);
		return redirect(routes.ProjectController.detail(hoursWorkedForm.get().getProjectId()));
	}
	
	@Transactional(readOnly=true)
	public static final Result isProjectShortcutFree(String string) {
		ObjectNode res = Json.newObject();
		Project project = DAOs.getProjectDao().getProjectByShortcut(string);
		if (project == null) {
			res.put("isFree", 1);
			res.put("project", -1);
		} else {
			res.put("isFree", 0);
			res.put("project", project.getProjectId());
		}
		Logger.debug("Response {} is sending to client.", res);
		return ok(res);
	}
	
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - back to list
	 * @return
	 */
	private static List<MenuDto> getBackToListMenu(User user) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		if (SecurityService.hasAccess(user, ActionsEnum.PROJECT_SHOW_ALL)) {
			MenuDto back = new MenuDto();
			back.setGlyphicon("triangle-left");
			back.setLabel("Zpět na seznam projektů");
			back.setUrl(routes.ProjectController.showAll(0).absoluteURL(request()));
			result.add(back);
		}
		return result;
	}
	
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - add new
	 * @return
	 */
	private static List<MenuDto> getMainMenu(User user) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		if (SecurityService.hasAccess(user, ActionsEnum.PROJECT_CREATE)) {
			MenuDto newProject = new MenuDto();
			newProject.setGlyphicon("plus");
			newProject.setLabel("Přidat projekt");
			newProject.setUrl(routes.ProjectController.create().absoluteURL(request()));
			result.add(newProject);
		}
		return result;		
	}
	
	private static Boolean isProjectManager(Project project, User user) {
		for (UserOnProject uop : project.getUserOnProject()) {
			if (!uop.getVisible()) continue;
			if (user.equals(uop.getUser())) {
				return EnumerationWithKeys.PROJECT_MANAGER_KEY.equals(uop.getTypeUserOnProject().getKey());
			}
		}
		Logger.debug("User is not on project with id {} ", project.getProjectId());
		return null;
	}
	
}
