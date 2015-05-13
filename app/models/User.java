package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SQA_USER")
public class User extends AbstractVersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String username;
    public String password;
    @OneToMany(mappedBy="user")
    private List<RoleInBusiness> roleInBusiness = new ArrayList<>();
    public String degree;
    public String email;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    @Column(length = 500)
    public String bio;
    
	@ManyToOne(fetch=FetchType.EAGER)
    public StateUser stateUser;

	@OneToMany(fetch=FetchType.EAGER)
	public Set<Knowledge> knowledges;

	@OneToMany(fetch=FetchType.EAGER)
	public Set<AcademicWork> academicWorks;

    public User(){}

    public User(String username, String password, String firstName, String lastName, String degree, StateUser stateUser, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.stateUser = stateUser;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<RoleInBusiness> getRoleInBusiness() {
		return roleInBusiness;
	}

	public void setRoleInBusiness(List<RoleInBusiness> roleInBusiness) {
		this.roleInBusiness = roleInBusiness;
	}

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public StateUser getStateUser() {
		return stateUser;
	}

	public void setStateUser(StateUser stateUser) {
		this.stateUser = stateUser;
	}

	public void setKnowledges(Set<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	public void addKnowledge(Knowledge knowledge) {
		this.knowledges.add(knowledge);
	}

	public void removeKnowledge(Knowledge knowledge) {
		this.knowledges.remove(knowledge);
	}

	public Set<Knowledge> getKnowledges() {
		return this.knowledges;
	}

	public Set<AcademicWork> getAcademicWorks() {
		return academicWorks;
	}

	public User setAcademicWorks(Set<AcademicWork> academicWorks) {
		this.academicWorks = academicWorks;
		return this;
	}

	public User addAcademicWork(AcademicWork academicWork) {
		this.academicWorks.add(academicWork);
		return this;
	}

	public User removeAcademicWork(AcademicWork academicWork) {
		this.academicWorks.remove(academicWork);
		return this;
	}
}
