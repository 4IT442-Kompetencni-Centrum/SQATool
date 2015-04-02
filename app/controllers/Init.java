package controllers;

import daos.impl.DAOs;
import models.TypeRoleInBusiness;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.EnumerationWithKeys;
import views.html.index;

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
}
