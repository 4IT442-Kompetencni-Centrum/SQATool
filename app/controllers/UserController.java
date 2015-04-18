package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class UserController extends Controller {

	public static Result show(Long id) {
		//TODO mcech
		return ok();
	}
	
	/**
	 * Action returns JSON array for ajax calls (autocomplete)
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getUserByNamePattern(String query) {
		//TODO tmichalicka
		return ok();
	}
}
