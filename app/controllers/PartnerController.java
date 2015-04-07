package controllers;

import java.util.List;

import models.Partner;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
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
		//TODO tmichalicka
		return null;
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
	 * Action deletes project.
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly=false)
	public static Result delete(Long projectId) {
		//TODO tmichalicka
		return null;
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
}
