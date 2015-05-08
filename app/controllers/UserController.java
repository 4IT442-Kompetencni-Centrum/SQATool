package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.routes;
import daos.UserDao;
import models.*;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ActionsEnum;
import service.Configuration;
import service.SecurityService;
import views.data.MenuDto;
import views.formData.NewMemberForm;
import views.html.knowledge.knowledgeForm;
import views.html.knowledge.knowledgeList;
import views.html.user.detail;
import views.html.user.edit;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;
import forms.RewardForm;
import forms.UsersForm;

public class UserController extends Controller {

    static Form<UsersForm> usersForm = Form.form(UsersForm.class);

    private static List<StateUser> userStates = DAOs.getStateUserDao().findAll();
    private static List<TypeRoleInBusiness> userRoles = DAOs.getTypeRoleInBusinessDao().findAll();
	
	@Transactional()
	public static Result show(Long id){
		Logger.debug(id + "");
		User _user = DAOs.getUserDao().findById(id);
		Logger.debug(_user+"");


		List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(_user.getId());
		Form<RewardForm> rewardForm = Form.form(RewardForm.class);

		return ok(detail.render(_user,projects,rewardForm, getBackToListMenu(_user)));
	}
		
    @Transactional(readOnly = false)
    public static Result edit(Long id){
        Form<NewMemberForm> bindForm = Form.form(NewMemberForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            return redirect(routes.UserController.showEditForm(id));
        }
        User user = bindForm.get().getMember(id);
        DAOs.getUserDao().update(user);
        return redirect(routes.UserController.showAllUsers(0));
    }
	
	@Transactional(readOnly = false)
    //@Authorize(action = ActionsEnum.USER_EDIT_PROFILE)
    public static Result showEditForm(Long userId) {
		User _user = DAOs.getUserDao().findById(userId);
		return ok(edit.render(_user, getBackToListMenu(_user), "Upravit člena", userStates, userRoles));
	}

    @Transactional(readOnly = false)
    public static Result showCreateForm(){
        User user = SecurityService.fetchUser(session("authid"));
        return ok(views.html.user.addMember.render(user, getBackToListMenu(user), "Přidat člena", userStates, userRoles));
    }
	
	@Transactional(readOnly = false)
	public static Result create(){
		Form<NewMemberForm> bindForm = Form.form(NewMemberForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            return redirect(routes.UserController.showCreateForm());
        }
        NewMemberForm filledForm = bindForm.get();
        StateUser stateUser = DAOs.getStateUserDao().findByKey(filledForm.status);
        TypeRoleInBusiness userRoleType = DAOs.getTypeRoleInBusinessDao().findById(Long.valueOf(filledForm.role));
        RoleInBusiness userRole = new RoleInBusiness(userRoleType);
        User user = new User(filledForm.firstname, filledForm.lastname, filledForm.xname, filledForm.degree, stateUser ,filledForm.email, filledForm.phonenumber);
        user.getRoleInBusiness().add(userRole);
        DAOs.getUserDao().create(user);
        return redirect(routes.UserController.showAllUsers(0));
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

    // Petr
    @Transactional
    public static Result showAllUsers(Integer page){
        page = page != null ? page : 0;
        List<User> membersList = DAOs.getUserDao().getAllMembers(page* Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
        return ok(views.html.clenove.members.render(membersList, getMainMenu(), 1, 1));
    }

    @Transactional
    public static Result deleteMember(Long id){
        User user = SecurityService.fetchUser(session().get("authid"));
        if (!SecurityService.hasAccess(user, ActionsEnum.MEMBER_DELETE)) {
            return redirect(routes.Application.accessDenied());
        }
        User candidate = DAOs.getUserDao().findById(id);
        if(candidate != null){
            DAOs.getUserDao().delete(candidate);
            Logger.debug("Candidate with id {} was successfully deleted.", id);
            return redirect(routes.UserController.showAllUsers(0));
        }
        Logger.info("Member with id was not found, delete opereation was not successful.", id);
        return redirect(routes.UserController.memberNotFound(id));
    }

    @Transactional
    public static Result memberNotFound(Long id){
        User user = SecurityService.fetchUser(session("authid"));
        return ok(views.html.clenove.memberNotFound.render(id, getBackToListMenu(user)));
    }

    private static List<MenuDto> getMainMenu() {
        List<MenuDto> result = new ArrayList<MenuDto>();
        User user = SecurityService.fetchUser(session("authid"));
        if (SecurityService.hasAccess(user, ActionsEnum.MEMBER_ADD)) {
            MenuDto newMember = new MenuDto();
            newMember.setGlyphicon("plus");
            newMember.setLabel("Přidat člena");
            newMember.setUrl("/uzivatel/novy");
            result.add(newMember);
        }
        return result;
    }

    private static List<MenuDto> getBackToListMenu(User user) {
        List<MenuDto> result = new ArrayList<MenuDto>();
        if (SecurityService.hasAccess(user, ActionsEnum.MEMBER_SHOW_ALL)) {
            MenuDto back = new MenuDto();
            back.setGlyphicon("triangle-left");
            back.setLabel("Zpět na seznam členů");
            back.setUrl("/clenove");
            result.add(back);
        }
        return result;
    }

}
