package controllers;

import models.User;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.detail;
import daos.impl.DAOs;


public class UserController extends Controller {
	
	@Transactional()
	public static Result show(Long id){
		Logger.debug(id + "");
		User _user = DAOs.getUserDao().findById(id);
		Logger.debug(_user+"");
		return ok(detail.render(_user));
		
	}
		
}
