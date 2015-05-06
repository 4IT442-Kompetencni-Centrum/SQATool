package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.OptimisticLockException;

import models.ContactPerson;
import models.Partner;
import models.Project;
import models.User;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import service.ActionsEnum;
import service.Configuration;
import service.PartnerConverter;
import service.SecurityService;
import views.data.ContactPersonDto;
import views.data.MenuDto;
import views.data.PartnerDto;
import views.html.partners.partnerDetail;
import views.html.partners.partnerNotFound;
import views.html.partners.partners;
import views.html.partners.partnersCreate;
import views.html.partners.partnersEdit;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;
/**
 * Controller for actions related to Partner and ContactPerson
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @see Partner
 * @see PartnerDto
 * @see ContactPerson
 * @see ContactPersonDto
 */
@Security.Authenticated(Secured.class)
public class PartnerController extends Controller {
	/**
	 * Action shows all projects where user participates
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result showAll(Integer page) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_SHOW_ALL)) {
			return redirect(routes.Application.accessDenied());
		}
		if (page == null) {
			page = 0;
			Logger.debug("No page number is given. Setting 0 (first page).");
		}
		List<Partner> proj= DAOs.getPartnerDao().getAllPartners(page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
		
		Integer totalPartners = DAOs.getPartnerDao().getNumberOfPartners();
		Integer numberOfPages = totalPartners % Configuration.PAGE_SIZE == 0 ? totalPartners/Configuration.PAGE_SIZE : totalPartners/Configuration.PAGE_SIZE + 1; 
		Logger.debug("Page with list of partners is shown. Number of partners id db is {}", totalPartners);
		return ok(partners.render(PartnerConverter.convertListToDto(proj), getMainMenu(user), numberOfPages, page, user));
	
	}
	
	/**
	 * Action shows form for adding new partner
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result create() {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_CREATE)) {
			return redirect(routes.Application.accessDenied());
		}		
		Form<PartnerDto> partnerForm = Form.form(PartnerDto.class).fill(new PartnerDto());
		Logger.debug("Page with form for creating new partner is shown.");
		return ok();//partnersEdit.render(partnerForm, getBackToListMenu(user), false, "Přidat partnera", routes.PartnerController.saveNewPartner().absoluteURL(request())));
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long partnerId) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_EDIT)) {
			return redirect(routes.Application.accessDenied());
		}	
		PartnerDto dto = PartnerConverter.convertToDto(DAOs.getPartnerDao().findById(partnerId));
		if (dto == null) {
			Logger.info("Partner with id {} was not found.", partnerId);
			return redirect(routes.PartnerController.partnerNotFound(partnerId));
		}
		Form<PartnerDto> partnerForm = Form.form(PartnerDto.class).fill(dto);
		Logger.debug("Page with form for editing partner is shown. Edited partner has id {} and name {}.", dto.getPartnerId(), dto.getName());
		return ok();//partnersEdit.render(partnerForm, getBackToListMenu(user), false, "Editovat partnera", routes.PartnerController.updatePartner(false).absoluteURL(request())));
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result updatePartner(Boolean forceNext) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_EDIT)) {
			return redirect(routes.Application.accessDenied());
		}	
		Form<PartnerDto> partnerForm = Form.form(PartnerDto.class).bindFromRequest();
		Partner partner = PartnerConverter.convertToEntity(partnerForm.get());
		if (forceNext != null && forceNext) {
			Partner edited = DAOs.getPartnerDao().findById(partnerForm.get().getPartnerId());
			partner.setVersion(edited.getVersion());
			Logger.debug("Force rewrite partner data is set. Previous modifications will be deleted.");
		}
		try {
			List<ContactPerson> cp = DAOs.getContactPersonDao().getContactPersonForPartner(partner);
			for (ContactPerson person : partner.getContactPersons()) {
				person.setPartner(partner);
				if (person.getContactPersonId() == null) {
					DAOs.getContactPersonDao().create(person);
				} else {
					removeCPFromList(cp, person.getContactPersonId());
					DAOs.getContactPersonDao().update(person);
				}
			}
			for (ContactPerson conPerson : cp) {
				DAOs.getContactPersonDao().delete(conPerson);
			}
			partner.setVisible(true);
			DAOs.getPartnerDao().update(partner);
		} catch (OptimisticLockException e) {
			Logger.info("Partner {} was edited by another user. ", partnerForm.get());
			return ok();//partnersEdit.render(partnerForm, getBackToListMenu(user), true, "Editovat partnera", routes.PartnerController.updatePartner(true).absoluteURL(request())));
		}
		Logger.debug("Partner update operation was called.");
		return redirect(routes.PartnerController.showAll(0));
	}
	
	private static void removeCPFromList(List<ContactPerson> cp, Long contactPersonId) {
		if (contactPersonId == null) return;
		for (ContactPerson conPerson : cp) {
			if (contactPersonId == conPerson.getContactPersonId()) {
				cp.remove(conPerson);
				return;
			}
		}
	}
	
	/**
	 * Action deletes partner.
	 * @param partner
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long partnerId) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_DELETE)) {
			return redirect(routes.Application.accessDenied());
		}	
		Partner partner = DAOs.getPartnerDao().findById(partnerId);
		if (partner != null) {
			partner.setProjects(new HashSet<Project>());
			DAOs.getPartnerDao().delete(partner);
		} else {
			Logger.info("Partner with id was not found, delete opereation was not successful.", partnerId);
			return redirect(routes.PartnerController.partnerNotFound(partnerId));
		}
		Logger.debug("Delete operation was called to partner with id {}.", partnerId);
		return redirect(routes.PartnerController.showAll(0).absoluteURL(request()));
	}
	/**
	 * Action for ajax calls. It returns json array of partners whose names matched given query.
	 * @param substr
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result getPartnersByPattern(String substr) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_SHOW_ALL)) {
			return redirect(routes.Application.accessDenied());
		}	
		List<Partner> partners = DAOs.getPartnerDao().findByName(substr);
		Logger.debug("Number of results for query {} is {}.", substr, partners.size());
		
		ObjectNode res = Json.newObject();
		ArrayNode result = res.arrayNode();
		for (Partner p : partners) {
			ObjectNode tmp = Json.newObject();
			tmp.put("value", p.getName());
			tmp.put("id", p.getPartnerId());
			tmp.put("tokens", Json.newObject().arrayNode().add(p.getName()));
			result.add(tmp);
		}
		Logger.debug("Response {} is sending to client.", result);
		return ok(result);
	}
	/**
	 * Action displays detail of partner
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result detail(Long partnerId) {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_DETAIL)) {
			return redirect(routes.Application.accessDenied());
		}	
		Partner partner = DAOs.getPartnerDao().findById(partnerId);
		if (partner == null) {
			Logger.info("Partner with id {} was not found, detail can not be shown.", partnerId);
			return redirect(routes.PartnerController.partnerNotFound(partnerId));
		}
		Logger.debug("Partner detail page is shown.");
		return ok();//partnerDetail.render(PartnerConverter.convertToDto(partner), getBackToListMenu(user)));
	}
	/**
	 * Action shows page partner not found.
	 * @param partnerId
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result partnerNotFound(Long partnerId) {
		User user = SecurityService.fetchUser(session("authid"));
		Logger.debug("Partner not found page is shown. Requested id was {}.", partnerId);
		return ok(partnerNotFound.render(partnerId, getBackToListMenu(user)));
	}
	
	/**
	 * Action saves new project
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result saveNewPartner() {
		User user = SecurityService.fetchUser(session("authid"));
		if (!SecurityService.hasAccess(user, ActionsEnum.PARTNER_CREATE)) {
			return redirect(routes.Application.accessDenied());
		}	
		Form<PartnerDto> partnerForm = Form.form(PartnerDto.class).bindFromRequest();
		Partner newPartner = PartnerConverter.convertToEntity(partnerForm.get());
		newPartner.setVisible(true);
		for (ContactPerson person : newPartner.getContactPersons()) {
			person.setPartner(newPartner);
			person.setVisible(true);
			DAOs.getContactPersonDao().create(person);
		}
		DAOs.getPartnerDao().create(newPartner);
		Logger.debug("Action for saving data of new partner was called.");
		return redirect(routes.PartnerController.showAll(0).absoluteURL(request()));
	}
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - back to list
	 * @return
	 */
	private static List<MenuDto> getBackToListMenu(User user) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		if (SecurityService.hasAccess(user, ActionsEnum.PARTNER_SHOW_ALL)) {
			MenuDto back = new MenuDto();
			back.setGlyphicon("triangle-left");
			back.setLabel("Zpět na seznam partnerů");
			back.setUrl(routes.PartnerController.showAll(0).absoluteURL(request()));
			result.add(back);
		}
		return result;
	}
	/**
	 * Method returns list of items to left side menu. This implementation returns one item - add new
	 * @return
	 */
	private static List<MenuDto> getMainMenu(User user) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		if (SecurityService.hasAccess(user, ActionsEnum.PARTNER_CREATE)) {
			MenuDto newProject = new MenuDto();
			newProject.setGlyphicon("plus");
			newProject.setLabel("Přidat partnera");
			newProject.setUrl(routes.PartnerController.create().absoluteURL(request()));
			result.add(newProject);
		}
		
		return result;		
	}
}
