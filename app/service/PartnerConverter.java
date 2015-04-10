package service;

import java.util.ArrayList;
import java.util.List;

import models.Partner;
import views.data.PartnerDto;

public class PartnerConverter {
	public static PartnerDto convertToDto(Partner orig) {
		PartnerDto res = new PartnerDto();
		res.setCity(orig.getCity());
		res.setDescription(orig.getDescription());
		res.setHouseNumber(orig.getHouseNumber());
		res.setIc(orig.getIc());
		res.setName(orig.getName());
		res.setPartnerId(orig.getPartnerId());
		res.setStreet(orig.getStreet());
		return res;
	}
	
	public static Partner convertToEntity(PartnerDto orig) {
		Partner res = new Partner();
		res.setCity(orig.getCity());
		res.setDescription(orig.getDescription());
		res.setHouseNumber(orig.getHouseNumber());
		res.setIc(orig.getIc());
		res.setName(orig.getName());
		res.setPartnerId(orig.getPartnerId());
		res.setStreet(orig.getStreet());
		return res;
	}
	
	public static List<PartnerDto> convertListToDto(List<Partner> orig) {
		List<PartnerDto> res = new ArrayList<PartnerDto>();
		for (Partner p : orig) {
			res.add(convertToDto(p));
		}
		return res;
	}
}
