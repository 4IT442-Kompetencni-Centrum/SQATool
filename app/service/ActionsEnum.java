package service;
/**
 * Enum with actions
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public enum ActionsEnum {
	//project actions
	PROJECT_SHOW_ALL,
	PROJECT_CREATE,
	PROJECT_DETAIL,
	PROJECT_EDIT,
	PROJECT_DELETE,
	
	//partner actions
	PARTNER_SHOW_ALL,
	PARTNER_CREATE,
	PARTNER_DETAIL,
	PARTNER_EDIT,
	PARTNER_DELETE,
	
	//activities actions
	ACTIVITY_SHOW_ALL,
	ACTIVITY_SHOW,
	ACTIVITY_ADD,
	ACTIVITY_EDIT,
	ACTIVITY_DELETE,

	//rewards actions
	REWARD_SHOW_ALL,
	REWARD_SHOW,
	REWARD_ADD,
	REWARD_EDIT,
	REWARD_DELETE,

	//user actions
	MEMBER_SHOW_ALL,
    MEMBER_SHOW,
    MEMBER_ADD,
    MEMBER_EDIT,
    MEMBER_DELETE,
    MEMBER_AUTOCOMPLETE,

	USER_EDIT_PROFILE,
	//TODO pkadlec

	//enumeration actions
	ENUMERATION_SHOW_ALL,
	ENUMERATION_EDIT

}
