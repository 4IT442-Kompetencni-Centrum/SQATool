package controllers;

import java.util.List;

import models.Project;
import play.mvc.Result;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import views.html.*;
import daos.impl.DAOs;

public class ProjectController extends Controller{

	private static final Integer PAGE_SIZE = 30;
	
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
		
		return ok(projects.render(proj));
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
