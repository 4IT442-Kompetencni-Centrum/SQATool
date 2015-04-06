package controllers;

import models.Partner;
import models.TypeRoleInBusiness;
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
}
