package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Project;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.ProjectConverter;
import views.data.ProjectDto;
import views.html.projectDetail;
import views.html.projects;
import views.html.projectsCreate;
import daos.impl.DAOs;

public class ProjectController extends Controller{

	private static final Integer PAGE_SIZE = 20;
	
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
		List<Project> proj= DAOs.getProjectDao().getAllProject(page * PAGE_SIZE, PAGE_SIZE);
		
		Integer totalProjects = DAOs.getProjectDao().getNumberOfProjects();
		Integer numberOfPages = totalProjects % PAGE_SIZE == 0 ? totalProjects/PAGE_SIZE : totalProjects/PAGE_SIZE + 1; 
		
		return ok(projects.render(proj, numberOfPages, page));
	}
	
	/**
	 * Action shows form for adding new project
	 * @return
	 */
	public static Result create() {
		Form<ProjectDto> userForm = Form.form(ProjectDto.class);
		return ok(projectsCreate.render(userForm));
	}
	
	@Transactional(readOnly=false)
	public static Result saveNewProject() {
		Form<ProjectDto> userForm = Form.form(ProjectDto.class).bindFromRequest();
		Project newProject = ProjectConverter.convertToEntity(userForm.get());
		newProject.setVisible(true);
		DAOs.getProjectDao().create(newProject);
		return redirect("/projekty");
	}
	
	@Transactional(readOnly=false)
	public static Result detail(Long projectId) {
		Project project = DAOs.getProjectDao().findById(projectId);
		if (project == null) {
			return redirect("/projekty/nenalezen/"+projectId);
		} else {
			return ok(projectDetail.render(project, null, new ArrayList<User>()));
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
		return redirect("/projekty");
	}
	
	/**
	 * Action shows page which informs that project with given id was not found.
	 * @param projectId
	 * @return
	 */
	public static Result projectNotFound(Long projectId) {
		return ok(views.html.projectNotFound.render(projectId));
	}
	
}
