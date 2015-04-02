package daos.impl;

import daos.ContactPersonDao;
import daos.HoursWorkedDao;
import daos.PartnerDao;
import daos.ProjectDao;
import daos.TypeRoleInBusinessDao;
import daos.UserOnProjectDao;

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
	
	
	
}
