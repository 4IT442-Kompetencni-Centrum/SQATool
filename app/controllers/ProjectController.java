package controllers;

import java.util.List;

import models.Project;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.ProjectConverter;
import views.data.ProjectDto;
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
		DAOs.getProjectDao().create(ProjectConverter.convertToEntity(userForm.get()));
		return redirect("/projekty");
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long projectId) {
		//TODO tmichalicka
		return null;
	}
	
	/**
	 * Action deletes project.
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long projectId) {
		//TODO tmichalicka
		return null;
	}
	
}
