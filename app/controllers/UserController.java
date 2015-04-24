package controllers;

import java.util.List;

import models.User;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ActionsEnum;
import service.SecurityService;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;

public class UserController extends Controller {

	public static Result show(Long id) {
		//TODO mcech - pozor, vede sem odkaz z detailu projektu, po mergi overit.
		return ok();
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
