package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Partner;
import models.Project;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.Configuration;
import service.PartnerConverter;
import views.data.MenuDto;
import views.html.partners.partnerDetail;
import views.html.partners.partners;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.impl.DAOs;

public class PartnerController extends Controller {
	/**
	 * Action shows all projects where user participates
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public static Result showAll(Integer page) {
		if (page == null) {
			page = 0;
		}
		List<Partner> proj= DAOs.getPartnerDao().getAllPartners(page * Configuration.PAGE_SIZE, Configuration.PAGE_SIZE);
		
		Integer totalPartners = DAOs.getPartnerDao().getNumberOfPartners();
		Integer numberOfPages = totalPartners % Configuration.PAGE_SIZE == 0 ? totalPartners/Configuration.PAGE_SIZE : totalPartners/Configuration.PAGE_SIZE + 1; 
		return ok(partners.render(PartnerConverter.convertListToDto(proj), getMainMenu(), numberOfPages, page));
	
	}
	
	/**
	 * Action shows form for adding new partner
	 * @return
	 */
	public static Result create() {
		return ok();
	}
	
	/**
	 * Action show page where project attributes can be modified
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result edit(Long projectId) {
		//TODO tmichalicka
		return null;
	}
	
	/**
	 * Action deletes partner.
	 * @param partner
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long partnerId) {
		Partner partner = DAOs.getPartnerDao().findById(partnerId);
		if (partner != null) {
			partner.setProjects(new ArrayList<Project>());
			DAOs.getPartnerDao().delete(partner);
		}
		return redirect(routes.PartnerController.showAll(0).absoluteURL(request()));
	}
	
	@Transactional(readOnly=true)
	public static Result getPartnersByPattern(String substr) {
		
		Logger.debug("Requested string is {}.", substr);
		
		List<Partner> partners = DAOs.getPartnerDao().findByName(substr);
		Logger.debug("Number of results is {}.", partners.size());
		
		ObjectNode res = Json.newObject();
		ArrayNode result = res.arrayNode();
		for (Partner p : partners) {
			ObjectNode tmp = Json.newObject();
			tmp.put("value", p.getName());
			tmp.put("id", p.getPartnerId());
			tmp.put("tokens", Json.newObject().arrayNode().add(p.getName()));
			result.add(tmp);
		}

		return ok(result);
	}
	
	@Transactional(readOnly=false)
	public static Result detail(Long partnerId) {
		Partner partner = DAOs.getPartnerDao().findById(partnerId);
		if (partner == null) {
			return redirect(routes.PartnerController.partnerNotFound(partnerId));
		}
		return ok(partnerDetail.render(partner, getBackToListMenu()));
	}
	
	public static Result partnerNotFound(Long partnerId) {
		return ok();
	}
	
	private static List<MenuDto> getBackToListMenu() {
		List<MenuDto> result = new ArrayList<MenuDto>();
		
		MenuDto back = new MenuDto();
		back.setGlyphicon("triangle-left");
		back.setLabel("Zpět na seznam partnerů");
		back.setUrl(routes.PartnerController.showAll(0).absoluteURL(request()));
		result.add(back);
		
		return result;
	}
	
	private static List<MenuDto> getMainMenu() {
		List<MenuDto> result = new ArrayList<MenuDto>();
		
		MenuDto newProject = new MenuDto();
		newProject.setGlyphicon("plus");
		newProject.setLabel("Přidat partnera");
		newProject.setUrl(routes.PartnerController.create().absoluteURL(request()));
		result.add(newProject);
		
		return result;		
	}
}
