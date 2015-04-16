package service;

import java.util.ArrayList;
import java.util.List;

import models.ContactPerson;
import views.data.ContactPersonDto;

public class ContactPersonConverter {
	public static ContactPerson convertertToEntity(ContactPersonDto orig) {
		ContactPerson res = new ContactPerson();
		res.setContactPersonId(orig.getContactPersonId());
		res.setEmail(orig.getEmail());
		res.setFirstName(orig.getFirstName());
		res.setLastName(orig.getLastName());
		res.setPhoneNumber(orig.getPhoneNumber());
		res.setVersion(orig.getVersion());
		return res;
	}
	
	public static ContactPersonDto convertertToDto(ContactPerson orig) {
		ContactPersonDto res = new ContactPersonDto();
		res.setContactPersonId(orig.getContactPersonId());
		res.setEmail(orig.getEmail());
		res.setFirstName(orig.getFirstName());
		res.setLastName(orig.getLastName());
		res.setPhoneNumber(orig.getPhoneNumber());
		res.setVersion(orig.getVersion());
		return res;
	}
	
	public static List<ContactPerson> convertListToEntity(List<ContactPersonDto> orig) {
		List<ContactPerson> res = new ArrayList<ContactPerson>();
		if (orig != null) {
			for (ContactPersonDto person : orig) {
				if (person.isNullOrEmpty()) continue;
				res.add(convertertToEntity(person));
			}
		}
		return res;
	}
	
	public static List<ContactPersonDto> convertListToDto(List<ContactPerson> orig) {
		List<ContactPersonDto> res = new ArrayList<ContactPersonDto>();
		if (orig != null) {
			for (ContactPerson person : orig) {
				res.add(convertertToDto(person));
			}
		}
		return res;
	}
}
