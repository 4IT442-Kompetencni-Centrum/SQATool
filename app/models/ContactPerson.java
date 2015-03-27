package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity ContactPerson
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_CONTACT_PERSON")
public class ContactPerson extends AbstractVersionedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactPersonId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	@ManyToOne(fetch=FetchType.EAGER)
	private Partner partner;
	
	
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
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	
	
}
