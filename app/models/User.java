package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SQA_USER")
public class User extends AbstractVersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String username;
    public String password;
    @OneToMany(mappedBy="user")
    private List<RoleInBusiness> roleInBusiness;
    public String degree;
    public String email;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    @Column(length = 500)
    public String bio;
    
	@ManyToOne(fetch=FetchType.EAGER)
    public StateUser stateUser;

    public User(){}

    public User(String userName, String password){
        this.username = userName;
        this.password = password;
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



	public static class Page{

        private final int pageSize;
        private final long totalRowCount;
        private final int pageIndex;
        private final List<User> userList;

        public Page(int pageSize, long totalRowCount, int pageIndex, List<User> userList){
            this.totalRowCount = totalRowCount;
            this.pageSize = pageSize;
            this.pageIndex = pageIndex;
            this.userList = userList;
        }

        public boolean hasPrev(){
            return pageIndex > 1;
        }

        public boolean hasNext(){
            return (totalRowCount / pageSize) >= pageIndex;
        }

        public int getPageSize(){
            return pageSize;
        }

        public long getTotalRowCount() {
            return totalRowCount;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public List<User> getUserList() {
            return userList;
        }
    }
}
