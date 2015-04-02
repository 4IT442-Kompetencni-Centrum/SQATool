package service;

import java.util.HashMap;
import java.util.List;

import models.RoleInBusiness;
import play.Logger;
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
	public static boolean hasAccess(List<RoleInBusiness> userRoles, ActionsEnum action) {
		if (userRoles == null || action == null) {
			Logger.error("Access denied. User has no permissions or action is null.");
			return false;
		}
		if (accessMap == null) {
			initAccessMap();
		}
		for (RoleInBusiness userRole : userRoles) {
			if (accessMap.get(action).get(userRole.getTypeRoleInBusiness().getKey())) {
				Logger.debug("Access granted for user with permissions {} for action", userRoles, action);
				return true;
			}
		}
		Logger.warn("Access denied for user with permissions {} for action {}", userRoles, action);
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
		HashMap<String, Boolean> projectCreateMap = new HashMap<String, Boolean>();
		projectCreateMap.put(EnumerationWithKeys.MEMBER_KEY, false);
		projectCreateMap.put(EnumerationWithKeys.MANAGER_KC_KEY, true);
		projectCreateMap.put(EnumerationWithKeys.HEAD_KC_KEY, true);
		projectCreateMap.put(EnumerationWithKeys.ADMIN_KEY, true);
		//TODO doplnit dalsi
		accessMap.put(ActionsEnum.PROJECT_MANAGEMENT, projectCreateMap);
	}
}
