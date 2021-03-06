package views.formData;

import daos.impl.DAOs;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by petr on 7.5.15.
 */
public class NewMemberForm {

    public Long id;
    public String firstname;
    public String lastname;
    public String username;
    public String password;
    public String degree;
    public String status;
    public String roleTypeId;
    public String email;
    public String phonenumber;

    public User getMember(Long id){
        User user = DAOs.getUserDao().findById(id);
        user.firstName = this.firstname;
        user.lastName = this.lastname;
        user.username = this.username;
        user.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        user.degree = this.degree;
        user.stateUser = DAOs.getStateUserDao().findByKey(this.status);
        user.email = this.email;
        user.phoneNumber = this.phonenumber;
        return user;
    }
}
