package controllers;

import play.api.mvc.Result;
import play.db.jpa.Transactional;
import play.mvc.Controller;

public class Partner extends Controller {
	/**
	 * Action shows all projects where user participates
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result showAll(Integer page) {
		//TODO tmichalicka
		return null;
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
