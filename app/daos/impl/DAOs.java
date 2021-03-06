package daos.impl;

import daos.*;

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
    private static TypeUserOnProjectDao typeUserOnProjectDao;
    private static StateHoursWorkedDao stateHoursWorkedDao;
    private static UsersKnowledgeDao usersKnowledgeDao;
    private static TypeKnowledgeDao typeKnowledgeDao;
    private static LevelOfKnowledgeDao levelOfKnowledgeDao;
	private static KnowledgeDao knowledgeDao;
	private static AcademicWorkDao academicWorkDao;
	private static StateAcademicWorkDao stateAcademicWorkDao;
	private static TypeAcademicWorkDao typeAcademicWorkDao;
    private static RoleInBusinessDao roleInBusinessDao;
    
    public static ProjectDao getProjectDao() {
		if (projectDao == null) {
			projectDao = new ProjectDaoImpl();
		}
		return projectDao;
	}
    
    public static UsersKnowledgeDao getUserKnowledgeDao() {
		if (usersKnowledgeDao == null) {
			usersKnowledgeDao = new UsersKnowledgeDaoImpl();
		}
		return usersKnowledgeDao;
	}
    
    public static TypeKnowledgeDao getTypeKnowledgeDao() {
		if (typeKnowledgeDao == null) {
			typeKnowledgeDao = new TypeKnowledgeDaoImpl();
		}
		return typeKnowledgeDao;
	}
    
    public static LevelOfKnowledgeDao getLevelOfKnowledgeDao() {
		if (levelOfKnowledgeDao == null) {
			levelOfKnowledgeDao = new LevelOfKnowledgeDaoImpl();
		}
		return levelOfKnowledgeDao;
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
    
    public static TypeUserOnProjectDao getTypeUserOnProject(){
        if(typeUserOnProjectDao == null){
        	typeUserOnProjectDao = new TypeUserOnProjectDaoImpl();
        }
        return typeUserOnProjectDao;
    }
  
    public static StateHoursWorkedDao getStateHoursWorkedDao(){
        if(stateHoursWorkedDao == null){
        	stateHoursWorkedDao = new StateHoursWorkedDaoImpl();
        }
        return stateHoursWorkedDao;
    }

	public static KnowledgeDao getKnowledgeDao() {
		if(knowledgeDao == null) {
			knowledgeDao = new KnowledgeDaoImpl();
		}
		return knowledgeDao;
	}

	public static AcademicWorkDao getAcademicWorkDao() {
		if(academicWorkDao == null) {
			academicWorkDao = new AcademicWorkDaoImpl();
		}
		return academicWorkDao;
	}

	public static StateAcademicWorkDao getStateAcademicWorkDao() {
		if(stateAcademicWorkDao == null) {
			stateAcademicWorkDao = new StateAcademicWorkDaoImpl();
		}
		return stateAcademicWorkDao;
	}

	public static TypeAcademicWorkDao getTypeAcademicWorkDao() {
		if(typeAcademicWorkDao == null) {
			typeAcademicWorkDao = new TypeAcademicWorkDaoImpl();
		}
		return typeAcademicWorkDao;
	}

    public static RoleInBusinessDao getRoleInBusinessDao(){
        if(roleInBusinessDao == null){
            roleInBusinessDao = new RoleInBusinessDaoImpl();
        }
        return roleInBusinessDao;
    }
}
