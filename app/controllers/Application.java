package controllers;

import daos.UserDao;
import models.Project;
import models.User;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.ActionsEnum;
import service.SecurityService;
import views.html.index;
import views.html.login;
import daos.impl.DAOs;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

    @Transactional
    public static Result index() {
        return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }

    @Transactional(readOnly=false)
    public static Result testDAO() {
    	User user = new User("aaa", "bbb");
    	SecurityService.hasAccess(user.getRoleInBusiness(), ActionsEnum.PROJECT_EDIT);
    	Project project = DAOs.getProjectDao().findById(1l);
    	project.setName("Updated name");
    	DAOs.getProjectDao().update(project);
    	System.out.println(project.getName());
    	Project newProject = new Project();
    	newProject.setVisible(true);
    	newProject.setName("Next test project");
    	DAOs.getProjectDao().create(newProject);
    	return ok(index.render("4IT442 - Software Quality Assurance Tool"));
    }
    
}
