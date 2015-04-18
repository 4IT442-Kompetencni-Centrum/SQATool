package daos.impl;

import daos.*;
import models.StateUser;

/**
 * Storage class for DAOs.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class DAOs {
	private static ProjectDao projectDao;
	private static ContactPersonDao contactPersonDao;
	private static HoursWorkedDao hoursWorkedDao;
	private static PartnerDao partnerDao;
	private static UserOnProjectDao userOnProjectDao;
	private static TypeRoleInBusinessDao typeRoleInBusinessDao;
    private static UserDao userDao;
	private static ActivityDao activityDao;
	private static TypeActivityDao typeActivityDao;
	private static StateActivityDao stateActivityDao;
	private static TypeRoleOnActivityDao typeRoleOnActivityDao;
	private static UserLoggedOnActivityDao userLoggedOnActivityDao;
	private static RewardDao rewardDao;
    private static StateUserDao stateUserDao;

	public static ProjectDao getProjectDao() {
		if (projectDao == null) {
			projectDao = new ProjectDaoImpl();
		}
		return projectDao;
	}

	public static ContactPersonDao getContactPersonDao() {
		if (contactPersonDao == null) {
			contactPersonDao = new ContactPersonDaoImpl();
		}
		return contactPersonDao;
	}

	public static HoursWorkedDao getHoursWorkedDao() {
		if (hoursWorkedDao == null) {
			hoursWorkedDao = new HoursWorkedDaoImpl();
		}
		return hoursWorkedDao;
	}

	public static PartnerDao getPartnerDao() {
		if (partnerDao == null) {
			partnerDao = new PartnerDaoImpl();
		}
		return partnerDao;
	}

	public static UserOnProjectDao getUserOnProjectDao() {
		if (userOnProjectDao == null) {
			userOnProjectDao = new UserOnProjectDaoImpl();
		}
		return userOnProjectDao;
	}

	public static TypeRoleInBusinessDao getTypeRoleInBusinessDao() {
		if (typeRoleInBusinessDao == null) {
			typeRoleInBusinessDao = new TypeRoleInBusinessDaoImpl();
		}
		return typeRoleInBusinessDao;
	}

    public static UserDao getUserDao(){
        if(userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static ActivityDao getActivityDao() {
        if (activityDao == null) {
            activityDao = new ActivityDaoImpl();
        }
        return activityDao;
    }

    public static TypeActivityDao getTypeActivityDao() {
        if (typeActivityDao == null) {
            typeActivityDao = new TypeActivityDaoImpl();
        }
        return typeActivityDao;
    }

	public static StateActivityDao getStateActivityDao() {
		if (stateActivityDao == null) {
			stateActivityDao = new StateActivityDaoImpl();
		}
		return stateActivityDao;
	}

    public static TypeRoleOnActivityDao getTypeRoleOnActivityDao() {
        if (typeRoleOnActivityDao == null) {
            typeRoleOnActivityDao = new TypeRoleOnActivityDaoImpl();
        }
        return typeRoleOnActivityDao;
    }

    public static UserLoggedOnActivityDao getUserLoggedOnActivityDao() {
        if (userLoggedOnActivityDao == null) {
            userLoggedOnActivityDao = new UserLoggedOnActivityDaoImpl();
        }
        return userLoggedOnActivityDao;
    }

	public static RewardDao getRewardDao() {
		if(rewardDao == null) {
			rewardDao = new RewardDaoImpl();
		}
		return rewardDao;
	}

    public static StateUserDao getStateUserDao(){
        if(stateUserDao == null){
            stateUserDao = new StateUserDaoImpl();
        }
        return stateUserDao;
    }
}
