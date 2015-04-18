package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.OptimisticLockException;

import models.Partner;
import models.Project;
import models.User;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.Configuration;
import service.ProjectConverter;
import views.data.MenuDto;
import views.data.ProjectDto;
import views.html.projects.projectDetail;
import views.html.projects.projectNotFound;
import views.html.projects.projects;
import views.html.projects.projectsCreate;
import views.html.projects.projectsEdit;
import daos.impl.DAOs;
import play.mvc.Security;
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
		if (page == null) {
			page = 0;
			Logger.debug("No page number is given. Setting 0 (first page).");
		}
		List<Project> proj= DAOs.getProjectDao().getAllProject(page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
		
		Integer totalProjects = DAOs.getProjectDao().getNumberOfProjects();
		Integer numberOfPages = totalProjects % Configuration.PAGE_SIZE == 0 ? totalProjects/Configuration.PAGE_SIZE : totalProjects/Configuration.PAGE_SIZE + 1; 
		Logger.debug("Page with list of projects is shown. Number of projects id db is {}", totalProjects);
		return ok(projects.render(ProjectConverter.convertListToDto(proj), getMainMenu(), numberOfPages, page));
	}
	
	/**
	 * Action shows form for adding new project
	 * @return
	 */
	public static Result create() {
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class);
		Logger.debug("Page with form for creating new project is shown.");
		return ok(projectsCreate.render(projectForm, getBackToListMenu()));
	}
	/**
	 * Action saves new project
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result saveNewProject() {
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).bindFromRequest();
		Project newProject = ProjectConverter.convertToEntity(projectForm.get());
		newProject.setVisible(true);
		DAOs.getProjectDao().create(newProject);
		Logger.debug("Action for saving data of new project was called.");
		return redirect(routes.ProjectController.showAll(0).absoluteURL(request()));
	}
	/**
	 * Action shows detail of project
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result detail(Long projectId) {
		Project project = DAOs.getProjectDao().findById(projectId);
		if (project == null) {
			Logger.info("Partner with id {} was not found, detail can not be shown.", projectId);
			return redirect(routes.ProjectController.projectNotFound(projectId));
		} else {
			Logger.debug("Partner detail page is shown.");
			return ok(projectDetail.render(ProjectConverter.convertToDto(project), getBackToListMenu(), null, new ArrayList<User>()));
		}
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long projectId) {
		ProjectDto dto = ProjectConverter.convertToDto(DAOs.getProjectDao().findById(projectId));
		if (dto == null) {
			Logger.info("Project with id {} was not found.", projectId);
			return redirect(routes.ProjectController.projectNotFound(projectId));
		}
		Form<ProjectDto> projectForm = Form.form(ProjectDto.class).fill(dto);
		Logger.debug("Page with form for editing project is shown. Edited project has id {} and name {}.", dto.getProjectId(), dto.getName());
		return ok(projectsEdit.render(projectForm, getBackToListMenu(), false));
	}
	
	/**
	 * Action deletes project.
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long projectId) {
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
	public static Result projectNotFound(Long projectId) {
		Logger.debug("Project not found page is shown. Requested id was {}.", projectId);
		return ok(projectNotFound.render(projectId, getBackToListMenu()));
	}
	
	/**
	 * Action updates project data
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result updateProject(Boolean forceNext) {
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
			project.setVisible(true);
			Logger.debug("Project update: {}", project.toString());
			DAOs.getProjectDao().update(project);
		} catch (OptimisticLockException e) {
			Logger.info("Project {} was edited by another user. ", projectForm.get());
			List<String> col = projectForm.get().getPartnerNames();
			for (String tmp : projectForm.get().getPartnerNames()) {
				Logger.debug(tmp);
			}
			return ok(projectsEdit.render(projectForm, getBackToListMenu(), true));
		}
		Logger.debug("Project update operation was called.");
		return redirect(routes.ProjectController.showAll(0));
	}
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - back to list
	 * @return
	 */
	private static List<MenuDto> getBackToListMenu() {
		List<MenuDto> result = new ArrayList<MenuDto>();
		
		MenuDto back = new MenuDto();
		back.setGlyphicon("triangle-left");
		back.setLabel("Zpět na seznam projektů");
		back.setUrl(routes.ProjectController.showAll(0).absoluteURL(request()));
		result.add(back);
		
		return result;
	}
	
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - add new
	 * @return
	 */
	private static List<MenuDto> getMainMenu() {
		List<MenuDto> result = new ArrayList<MenuDto>();
		
		MenuDto newProject = new MenuDto();
		newProject.setGlyphicon("plus");
		newProject.setLabel("Přidat projekt");
		newProject.setUrl(routes.ProjectController.create().absoluteURL(request()));
		result.add(newProject);
		
		return result;		
	}
	
}
