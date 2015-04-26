package forms;

import daos.impl.DAOs;
import models.User;
import play.data.validation.Constraints;
import service.EnumerationWithKeys;

import javax.validation.Constraint;

public class UsersForm {

    protected Long userId;

    @Constraints.Required
    protected String firstName;

    @Constraints.Required
    protected String lastName;
    
    @Constraints.Required
    protected String degree;
    
    @Constraints.Required
    protected String phoneNumber;
 
    @Constraints.Required
    protected String email;

    public UsersForm() {
    }

    public UsersForm(User user){
		this.userId = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.degree = user.getDegree();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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

	public User getUser() {
        User user = null;

        if (this.getUserId() != null)
            user = DAOs.getUserDao().findById(this.getUserId());

        if (user == null)
            user = new User();
        
        user.setDegree(this.getDegree());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setPhoneNumber(this.getPhoneNumber());

        if (user.getId() == null)
        	user.setStateUser(DAOs.getStateUserDao().findByKey(EnumerationWithKeys.STATE_USER_APPLICANT));

        return user;
    }
}