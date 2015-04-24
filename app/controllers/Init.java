package controllers;

import models.Partner;
import models.StateUser;
import models.TypeRoleInBusiness;
import models.TypeUserOnProject;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.EnumerationWithKeys;
import views.html.index;
import daos.impl.DAOs;

public class Init extends Controller {

    @Transactional(readOnly=false)
    public static Result initRolesInBusiness() {
    	TypeRoleInBusiness admin = new TypeRoleInBusiness();
    	admin.setKey(EnumerationWithKeys.ADMIN_KEY);
    	admin.setValue("Administrátor");
    	DAOs.getTypeRoleInBusinessDao().create(admin);
    	
    	TypeRoleInBusiness manager = new TypeRoleInBusiness();
    	manager.setKey(EnumerationWithKeys.MANAGER_KC_KEY);
    	manager.setValue("Manažer KC");
    	DAOs.getTypeRoleInBusinessDao().create(manager);
    	
    	TypeRoleInBusiness head = new TypeRoleInBusiness();
    	head.setKey(EnumerationWithKeys.HEAD_KC_KEY);
    	head.setValue("Vedoucí KC");
    	DAOs.getTypeRoleInBusinessDao().create(head);
    	
    	TypeRoleInBusiness member = new TypeRoleInBusiness();
    	member.setKey(EnumerationWithKeys.MEMBER_KEY);
    	member.setValue("Člen");
    	DAOs.getTypeRoleInBusinessDao().create(member);
    	
    	return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
    
    @Transactional(readOnly=false)
    public static Result initPartners() {
    	Partner partner = new Partner();
    	partner.setVisible(true);
    	partner.setName("Cleverlance");
    	DAOs.getPartnerDao().create(partner);
    	
    	Partner partner2 = new Partner();
    	partner2.setVisible(true);
    	partner2.setName("Komerční banka");
    	DAOs.getPartnerDao().create(partner2);
    	
    	Partner partner3 = new Partner();
    	partner3.setVisible(true);
    	partner3.setName("Oracle");
    	DAOs.getPartnerDao().create(partner3);
    	
    	Partner partner4 = new Partner();
    	partner4.setVisible(true);
    	partner4.setName("Microsoft");
    	DAOs.getPartnerDao().create(partner4);
    	
    	Partner partner5 = new Partner();
    	partner5.setVisible(true);
    	partner5.setName("Google");
    	DAOs.getPartnerDao().create(partner5);
    	
    	Partner partner6 = new Partner();
    	partner6.setVisible(true);
    	partner6.setName("Facebook");
    	DAOs.getPartnerDao().create(partner6);
    	
    	return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }

    @Transactional(readOnly = false)
    public static Result initStateUser(){
        StateUser active = new StateUser();
        active.setKey(EnumerationWithKeys.STATE_USER_ACTIVE);
        active.setValue("Aktivní");
        DAOs.getStateUserDao().create(active);

        StateUser applicant = new StateUser();
        applicant.setKey(EnumerationWithKeys.STATE_USER_APPLICANT);
        applicant.setValue("Žadatel");
        DAOs.getStateUserDao().create(applicant);

        StateUser inactive = new StateUser();
        inactive.setKey(EnumerationWithKeys.STATE_USER_INACTIVE);
        inactive.setValue("Neaktivní");
        DAOs.getStateUserDao().create(inactive);

        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
    
    @Transactional(readOnly = false)
    public static Result initRolesOnProject(){
    	TypeUserOnProject manager = new TypeUserOnProject();
    	manager.setKey(EnumerationWithKeys.PROJECT_MANAGER_KEY);
    	manager.setValue("Vedoucí");
    	DAOs.getTypeUserOnProject().create(manager);
    	
    	TypeUserOnProject member = new TypeUserOnProject();
    	member.setKey(EnumerationWithKeys.PROJECT_MEMBER_KEY);
    	member.setValue("Člen");
    	DAOs.getTypeUserOnProject().create(member);
    	
    	return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
}
