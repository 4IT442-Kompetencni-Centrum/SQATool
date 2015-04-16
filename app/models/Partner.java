package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity Partner
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_PARTNER")
public class Partner extends AbstractVersionedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long partnerId;
	private String name;
	private Integer ic;
	private String city;
	private String street;
	private String houseNumber;
	private String description;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="partners")
	private Set<Project> projects;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="partner")
	private List<ContactPerson> contactPersons;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(List<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	@Override
	public String toString() {
		return "Partner [partnerId=" + partnerId + ", name=" + name + ", ic="
				+ ic + ", city=" + city + ", street=" + street
				+ ", houseNumber=" + houseNumber + ", description="
				+ description + ", projects=" + projects + ", contactPersons="
				+ contactPersons + "]";
	}
	
	
	
}
