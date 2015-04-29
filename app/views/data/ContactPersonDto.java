package views.data;

import play.data.validation.Constraints.Required;


public class ContactPersonDto {
	private Long contactPersonId;
	@Required
	private String firstName;
	@Required
	private String lastName;
	private String phoneNumber;
	@Required
	private String email;
	private Integer version;
	public Long getContactPersonId() {
		return contactPersonId;
	}
	public void setContactPersonId(Long contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public boolean isNullOrEmpty() {
		return (firstName == null || "".equals(firstName)) && 
			   (lastName == null || "".equals(lastName)) && 
			   (phoneNumber == null || "".equals(phoneNumber)) &&
			   (email == null || "".equals(email));
	}
	
	
}
