package controllers;

import java.util.List;

import models.LevelOfKnowledge;
import models.Project;
import models.TypeKnowledge;
import models.User;
import models.UsersKnowledge;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ActionsEnum;
import service.SecurityService;
import views.html.knowledge.knowledgeForm;
import views.html.knowledge.knowledgeList;
import views.html.user.detail;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;
import forms.RewardForm;

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


	@Transactional(readOnly = false)
	public static Result create(){
		return ok();
	}

	
	@Transactional
	public static Result listUsersKnowledge(Long userId){
		List<UsersKnowledge> usersKnowledge = DAOs.getUserKnowledgeDao().getUsersKnowledge(userId);
		List<TypeKnowledge> otherKnowledge = DAOs.getTypeKnowledgeDao().getAllKnowledge();
		List<LevelOfKnowledge> levels = DAOs.getLevelOfKnowledgeDao().getAllLevels();
		User user = DAOs.getUserDao().findById(userId);
		return ok(knowledgeList.render(usersKnowledge, otherKnowledge, levels, user));
	}
	
	@Transactional
	public static Result editUsersKnowledge(Long userId){
		List<UsersKnowledge> usersKnowledge = DAOs.getUserKnowledgeDao().getUsersKnowledge(userId);
		User user = DAOs.getUserDao().findById(userId);
		return ok(knowledgeForm.render(usersKnowledge, user));
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
