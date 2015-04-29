package service;

import java.util.HashMap;
import java.util.List;

import models.Project;
import models.RoleInBusiness;
import models.User;
import models.UserOnProject;
import play.Logger;
import daos.impl.DAOs;
/**
 * Secirity service for checking, if user has permission to do given actions.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class SecurityService {
	
	private static HashMap<ActionsEnum, HashMap<String, Boolean>> accessMap;
	
	/**
	 * Method checks, if user has permission to access given action
	 * @param userRoles
	 * @param action
	 * @return
	 */
	public static boolean hasAccess(User user, ActionsEnum action) {
		if (user == null) {
			Logger.warn("Access denied. User is null");
			return false;
		}
		List<RoleInBusiness> userRoles = user.getRoleInBusiness();
		if (userRoles == null || action == null) {
			Logger.error("Access denied. User has no permissions or action is null.");
			return false;
		}
		if (accessMap == null) {
			initAccessMap();
		}
		HashMap<String, Boolean> actionRow = accessMap.get(action);
		if (actionRow == null) {
			Logger.error("Access denied. No row for action {} was found in access map.", action);
			return false;
		}
		for (RoleInBusiness userRole : userRoles) {
			Boolean permission = actionRow.get(userRole.getTypeRoleInBusiness().getKey());
			if (permission == null) {
				Logger.error("Access denied. User has unknown role {}.", userRole);
				return false;
			}
			if (permission) {
				Logger.debug("Access granted for user with permissions {} for action", userRoles, action);
				return true;
			}
		}
		Logger.debug("Access denied for user with permissions {} for action {}", userRoles, action);
		return false;
	}
	/**
	 * Method loads user from db
	 * @param id
	 * @return
	 */
	public static User fetchUser(String id) {
		if (id == null) {
			return null;
		}
		Long userId = null;
		try {
			userId = Long.parseLong(id);
		} catch (Exception e) {
			Logger.error("Invalid char sequence {} is stored in session for id.", id);
			return null;
		}
		return DAOs.getUserDao().findById(userId);
	}
	/**
	 * Method checks if user can delete project
	 * @param project
	 * @param user
	 * @return
	 */	
	public static boolean canDeleteProject(Project project, User user) {
		return hasAccess(user, ActionsEnum.PROJECT_DELETE);
	}
	
	/**
	 * Method checks if user can modify project
	 * @param project
	 * @param user
	 * @return
	 */
	public static boolean canEditProject(Project project, User user) {
		if (hasAccess(user, ActionsEnum.PROJECT_DELETE)) {
			return true;
		}
		if (project.getUserOnProject() == null) {
			return false;
		}
		for (UserOnProject role : project.getUserOnProject()) {
			if (user.getId().equals(role.getUser().getId())) {
				return EnumerationWithKeys.PROJECT_MANAGER_KEY.equals(role.getTypeUserOnProject().getKey());
			}
		}
		return false;
	}
	
	/**
	 * Method initializes map with access rights
	 */
	private static void initAccessMap() {
		if (accessMap == null) {
			Logger.info("Initialization of accessRights map");
			accessMap = new HashMap<ActionsEnum, HashMap<String,Boolean>>();
		}
		
		initProjectAccessMap();
		initPartnerAccessMap();
		initActivityAccessMap();
		initRewardAccessMap();
		initMemberAccessMap();
	}

	private static void initProjectAccessMap() {
		HashMap<String, Boolean> projectShowAll = new HashMap<String, Boolean>();
		projectShowAll.put(EnumerationWithKeys.MEMBER_KEY, true);
		projectShowAll.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		projectShowAll.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectShowAll.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PROJECT_SHOW_ALL, projectShowAll);
		
		HashMap<String, Boolean> projectCreateMap = new HashMap<String, Boolean>();
		projectCreateMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		projectCreateMap.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		projectCreateMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectCreateMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PROJECT_CREATE, projectCreateMap);
		
		HashMap<String, Boolean> projectDetailMap = new HashMap<String, Boolean>();
		projectDetailMap.put(EnumerationWithKeys.MEMBER_KEY, true);
		projectDetailMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		projectDetailMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectDetailMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PROJECT_DETAIL, projectDetailMap);
		
		HashMap<String, Boolean> projectEditMap = new HashMap<String, Boolean>();
		projectEditMap.put(EnumerationWithKeys.MEMBER_KEY, true);
		projectEditMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		projectEditMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectEditMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PROJECT_EDIT, projectEditMap);
	
		HashMap<String, Boolean> projectDeleteMap = new HashMap<String, Boolean>();
		projectDeleteMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		projectDeleteMap.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		projectDeleteMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectDeleteMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PROJECT_DELETE, projectDeleteMap);
	}
	
	private static void initPartnerAccessMap() {
		HashMap<String, Boolean> partnerShowAllMap = new HashMap<String, Boolean>();
		partnerShowAllMap.put(EnumerationWithKeys.MEMBER_KEY, true);
		partnerShowAllMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		partnerShowAllMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		partnerShowAllMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PARTNER_SHOW_ALL, partnerShowAllMap);
		
		HashMap<String, Boolean> partnerCreateMap = new HashMap<String, Boolean>();
		partnerCreateMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		partnerCreateMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		partnerCreateMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		partnerCreateMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PARTNER_CREATE, partnerCreateMap);
		
		HashMap<String, Boolean> partnerDeleteMap = new HashMap<String, Boolean>();
		partnerDeleteMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		partnerDeleteMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		partnerDeleteMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		partnerDeleteMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PARTNER_DELETE, partnerDeleteMap);
		
		HashMap<String, Boolean> partnerDetailMap = new HashMap<String, Boolean>();
		partnerDetailMap.put(EnumerationWithKeys.MEMBER_KEY, true);
		partnerDetailMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		partnerDetailMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		partnerDetailMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PARTNER_DETAIL, partnerDetailMap);
		
		HashMap<String, Boolean> partnerUpdateMap = new HashMap<String, Boolean>();
		partnerUpdateMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		partnerUpdateMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		partnerUpdateMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		partnerUpdateMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.PARTNER_EDIT, partnerUpdateMap);
	}

	private static void initActivityAccessMap() {
		HashMap<String, Boolean> showAll = new HashMap<>();
		showAll.put(EnumerationWithKeys.MEMBER_KEY, true);
		showAll.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		showAll.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		showAll.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.ACTIVITY_SHOW_ALL, showAll);

		HashMap<String, Boolean> show = new HashMap<>();
		show.put(EnumerationWithKeys.MEMBER_KEY, true);
		show.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		show.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		show.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.ACTIVITY_SHOW, show);
		
		HashMap<String, Boolean> add = new HashMap<>();
		add.put(EnumerationWithKeys.MEMBER_KEY, false);
		add.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		add.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		add.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.ACTIVITY_ADD, add);

		HashMap<String, Boolean> edit = new HashMap<>();
		edit.put(EnumerationWithKeys.MEMBER_KEY, false);
		edit.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		edit.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		edit.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.ACTIVITY_EDIT, edit);

		HashMap<String, Boolean> delete = new HashMap<>();
		delete.put(EnumerationWithKeys.MEMBER_KEY, false);
		delete.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		delete.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		delete.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.ACTIVITY_DELETE, delete);
	}


	private static void initRewardAccessMap() {
		HashMap<String, Boolean> showAll = new HashMap<>();
		showAll.put(EnumerationWithKeys.MEMBER_KEY, false);
		showAll.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		showAll.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		showAll.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.REWARD_SHOW_ALL, showAll);

		HashMap<String, Boolean> show = new HashMap<>();
		show.put(EnumerationWithKeys.MEMBER_KEY, false);
		show.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		show.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		show.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.REWARD_SHOW, show);

		HashMap<String, Boolean> add = new HashMap<>();
		add.put(EnumerationWithKeys.MEMBER_KEY, false);
		add.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		add.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		add.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.REWARD_ADD, add);

		HashMap<String, Boolean> edit = new HashMap<>();
		edit.put(EnumerationWithKeys.MEMBER_KEY, false);
		edit.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		edit.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		edit.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.REWARD_EDIT, edit);

		HashMap<String, Boolean> delete = new HashMap<>();
		delete.put(EnumerationWithKeys.MEMBER_KEY, false);
		delete.put(EnumerationWithKeys.MANAGER_KC_KEY, false);
		delete.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		delete.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.REWARD_DELETE, delete);
	}
	
	private static void initMemberAccessMap() {
		HashMap<String, Boolean> showAll = new HashMap<>();
		showAll.put(EnumerationWithKeys.MEMBER_KEY, true);
		showAll.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		showAll.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		showAll.put(EnumerationWithKeys.ADMIN_KEY, true);
		accessMap.put(ActionsEnum.MEMBER_SHOW_ALL, showAll);		
	}
}
