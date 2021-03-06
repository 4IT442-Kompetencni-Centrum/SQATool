package views.data;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints.Required;

public class PartnerDto {
	private Long partnerId;
	@Required
	private String name;
	@Required
	private Integer ic;
	@Required
	private String city;
	@Required
	private String street;
	@Required
	private String houseNumber;
	private Integer version;
	private List<ContactPersonDto> contactPersons;
	
	public PartnerDto() {
		contactPersons = new ArrayList<ContactPersonDto>();
	}
	
	public Long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIc() {
		return ic;
	}
	public void setIc(Integer ic) {
		this.ic = ic;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "PartnerDto [partnerId=" + partnerId + ", name=" + name
				+ ", ic=" + ic + ", city=" + city + ", street=" + street
				+ ", houseNumber=" + houseNumber + ", version=" + version + "]";
	}
	public List<ContactPersonDto> getContactPersons() {
		return contactPersons;
	}
	public void setContactPersons(List<ContactPersonDto> contactPersons) {
		this.contactPersons = contactPersons;
	}
	
}
