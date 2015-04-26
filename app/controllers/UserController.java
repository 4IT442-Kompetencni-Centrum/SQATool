package controllers;

import java.util.List;

import forms.RewardForm;
import models.Project;
import models.User;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.detail;
import service.ActionsEnum;
import service.SecurityService;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;

public class UserController extends Controller {
	
	@Transactional()
	public static Result show(Long id){
		Logger.debug(id + "");
		User _user = DAOs.getUserDao().findById(id);
		Logger.debug(_user+"");


		List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(_user.getId());
		Form<RewardForm> rewardForm = Form.form(RewardForm.class);

		return ok(detail.render(_user,projects,rewardForm));
	}
		
	
	public static Result create() {
		//TODO mcech - pozor, vede sem odkaz z vytvoreni/editace projektu, po mergi overit.
		return ok();
	}
		
	
	/**
	 * Action returns JSON array for ajax calls (autocomplete)
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getUserByNamePattern(String query) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.MEMBER_SHOW_ALL)) {
			return redirect(routes.Application.accessDenied());
		}	
		List<User> users = DAOs.getUserDao().getUsersByQuery(query);
		Logger.debug("Number of results for query {} is {}.", query, users.size());
		
		ObjectNode res = Json.newObject();
		ArrayNode result = res.arrayNode();
		for (User u : users) {
			ObjectNode tmp = Json.newObject();
			tmp.put("value", u.getFirstName() + " " + u.getLastName());
			tmp.put("id", u.getId());
			tmp.put("tokens", Json.newObject().arrayNode().add(u.getFirstName()).add(u.getLastName()));
			result.add(tmp);
		}
		Logger.debug("Response {} is sending to client.", result);
		return ok(result);
	}
}
