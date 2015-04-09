package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Project;
import models.User;
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
import daos.impl.DAOs;

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
		}
		List<Project> proj= DAOs.getProjectDao().getAllProject(page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
		
		Integer totalProjects = DAOs.getProjectDao().getNumberOfProjects();
		Integer numberOfPages = totalProjects % Configuration.PAGE_SIZE == 0 ? totalProjects/Configuration.PAGE_SIZE : totalProjects/Configuration.PAGE_SIZE + 1; 
		return ok(projects.render(ProjectConverter.convertListToDto(proj), getMainMenu(), numberOfPages, page));
	}
	
	/**
	 * Action shows form for adding new project
	 * @return
	 */
	public static Result create() {
		Form<ProjectDto> userForm = Form.form(ProjectDto.class);
		return ok(projectsCreate.render(userForm, getBackToListMenu()));
	}
	/**
	 * Action saves new project
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result saveNewProject() {
		Form<ProjectDto> userForm = Form.form(ProjectDto.class).bindFromRequest();
		Project newProject = ProjectConverter.convertToEntity(userForm.get());
		newProject.setVisible(true);
		DAOs.getProjectDao().create(newProject);
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
			return redirect(routes.ProjectController.projectNotFound(projectId));
		} else {
			return ok(projectDetail.render(project, getBackToListMenu(), null, new ArrayList<User>()));
		}
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long projectId) {
		Project project = DAOs.getProjectDao().findById(projectId);
		return ok();
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
		}
		return redirect(routes.ProjectController.showAll(0).absoluteURL(request()));
	}
	
	/**
	 * Action shows page which informs that project with given id was not found.
	 * @param projectId
	 * @return
	 */
	public static Result projectNotFound(Long projectId) {
		return ok(projectNotFound.render(projectId, getBackToListMenu()));
	}
	
	private static List<MenuDto> getBackToListMenu() {
		List<MenuDto> result = new ArrayList<MenuDto>();
		
		MenuDto back = new MenuDto();
		back.setGlyphicon("triangle-left");
		back.setLabel("Zpět na seznam projektů");
		back.setUrl(routes.ProjectController.showAll(0).absoluteURL(request()));
		result.add(back);
		
		return result;
	}
	
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
