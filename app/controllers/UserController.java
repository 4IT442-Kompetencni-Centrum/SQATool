package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ActionsEnum;
import service.Configuration;
import service.SecurityService;
import views.data.MenuDto;
import views.formData.NewMemberForm;
import views.html.user.detail;
import views.html.user.edit;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;
import forms.RewardForm;
import forms.UsersForm;

import org.mindrot.jbcrypt.BCrypt;

public class UserController extends Controller {

    static Form<UsersForm> usersForm = Form.form(UsersForm.class);

    private static List<StateUser> userStates = DAOs.getStateUserDao().findAll();
    private static List<TypeRoleInBusiness> userRoles = DAOs.getTypeRoleInBusinessDao().findAll();
    private static Integer totalMembers = DAOs.getUserDao().getNumberOfMembers();
    private static Integer numberOfPages = totalMembers % Configuration.PAGE_SIZE == 0 ? totalMembers/Configuration.PAGE_SIZE : totalMembers/Configuration.PAGE_SIZE + 1;

    /**
     * Method shows the detail page of selected member. Projects, knowledges and personal information can be found inside
     * this detail page.
     * @param id
     * @return
     */
	@Transactional()
	public static Result show(Long id){
		User _user = DAOs.getUserDao().findById(id);
        User loggedUser = SecurityService.fetchUser(session("authid"));
        boolean canEdit = SecurityService.canEditMember(loggedUser);
        boolean canDelete = SecurityService.canDeleteMember(loggedUser);
		List<Project> projects = DAOs.getProjectDao().getAllProjectsForUser(_user.getId());
		Form<RewardForm> rewardForm = Form.form(RewardForm.class);
		return ok(detail.render(_user,projects,rewardForm, getBackToListMenu(loggedUser), canEdit, canDelete));
	}

    /**
     * Methods purpouse is to enable editing of selected user. It collects data and stores them inside a NewMemberForm
     * instance. If inserted data are correct, updates user with given id.
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public static Result edit(Long id){
        Form<NewMemberForm> bindForm = Form.form(NewMemberForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            return redirect(routes.UserController.showEditForm(id));
        }
        User user = bindForm.get().getMember(id);
        TypeRoleInBusiness typeRoleInBusiness = DAOs.getTypeRoleInBusinessDao().findById(Long.valueOf(bindForm.get().roleTypeId));
        RoleInBusiness userRole = new RoleInBusiness(typeRoleInBusiness, user);
        user.getRoleInBusiness().add(userRole);
        DAOs.getRoleInBusinessDao().create(userRole);
        DAOs.getUserDao().update(user);
        return redirect(routes.UserController.show(user.id));
    }

    /**
     * Method shows the edit form page.
     * @param userId
     * @return
     */
	@Transactional(readOnly = false)
    //@Authorize(action = ActionsEnum.USER_EDIT_PROFILE)
    public static Result showEditForm(Long userId) {
		User memberToEdit = DAOs.getUserDao().findById(userId);
        User user = SecurityService.fetchUser(session("authid"));
		return ok(edit.render(memberToEdit, getBackToListMenu(user), "Upravit člena", userStates, userRoles));
	}

    /**
     * Method show the create form page.
     * @return
     */
    @Transactional(readOnly = false)
    public static Result showCreateForm(){
        User user = SecurityService.fetchUser(session("authid"));
        return ok(views.html.user.addMember.render(user, getBackToListMenu(user), "Přidat člena", userStates, userRoles));
    }

    /**
     * Method performs creation of a new member. It gets the data and stores it inside a NewMemberInstance. Then validates
     * the data and if they are correct, creates a new member.
     * @return
     */
	@Transactional(readOnly = false)
	public static Result create(){
		Form<NewMemberForm> bindForm = Form.form(NewMemberForm.class).bindFromRequest();
        if(bindForm.hasErrors()){
            System.out.println("ERROR: " + bindForm.globalError().message());
            return redirect(routes.UserController.showCreateForm());
        }
        NewMemberForm filledForm = bindForm.get();
        StateUser stateUser = DAOs.getStateUserDao().findByKey(filledForm.status);
        User user = new User(
                filledForm.username,
                BCrypt.hashpw(filledForm.password, BCrypt.gensalt()),
                filledForm.firstname,
                filledForm.lastname,
                filledForm.degree,
                stateUser,
                filledForm.email,
                filledForm.phonenumber);
        TypeRoleInBusiness typeRoleInBusiness = DAOs.getTypeRoleInBusinessDao().findById(Long.valueOf(filledForm.roleTypeId));
        RoleInBusiness userRole = new RoleInBusiness(typeRoleInBusiness, user);
        user.getRoleInBusiness().add(userRole);
        DAOs.getRoleInBusinessDao().create(userRole);
        DAOs.getUserDao().create(user);
        return redirect(routes.UserController.showAllUsers(0));
	}

	
	/**
	 * Action returns JSON array for ajax calls (autocomplete)
	 * @param query
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getUserByNamePattern(String query) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.MEMBER_AUTOCOMPLETE)) {
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

    /**
     * Method gets the list of all members in db and sends this list to the views.html.clenove.members view.
     * @param page
     * @returnj
     */
    @Transactional
    public static Result showAllUsers(Integer page){
        User user = SecurityService.fetchUser(session("authid"));
        boolean canEdit = SecurityService.canEditMember(user);
        boolean canDelete = SecurityService.canDeleteMember(user);
        page = page != null ? page : 0;
        List<User> membersList = DAOs.getUserDao().getAllMembers(page* Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
        return ok(views.html.clenove.members.render(membersList, getMainMenu(), numberOfPages, page, canEdit, canDelete));
    }

    /**
     * Method for deleting member with given id. Checks if currently logged in user has permission to delete
     * members and if so, sets deleted member visibility to false so it cant be seen in the member list.
     * @param id
     * @return
     */
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

    /**
     * Redirects to notFound page if user with given id is not found.
     * @param id
     * @return
     */
    @Transactional
    public static Result memberNotFound(Long id){
        User user = SecurityService.fetchUser(session("authid"));
        return ok(views.html.clenove.memberNotFound.render(id, getBackToListMenu(user)));
    }

    /**
     * Method checks permissions of currently logged user to create and add new member.
     * Then creates the button to do so and returns it as MenuDto.
     * @return
     */
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

    /**
     * Method checks if currently logged in user has access to view the list of all members
     * and if so returns the button which is show inside the left column menu.
     * @param user
     * @return
     */
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
