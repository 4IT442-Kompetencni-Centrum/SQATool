package views.formData;

import daos.impl.DAOs;
import models.RoleInBusiness;
import models.User;

/**
 * Created by petr on 7.5.15.
 */
public class NewMemberForm {


    public Long id;
    public String firstname;
    public String lastname;
    public String xname;
    public String degree;
    public String status;
    public String role;
    public String email;
    public String phonenumber;

    public User getMember(Long id){
        User user = DAOs.getUserDao().findById(id);
        user.firstName = this.firstname;
        user.lastName = this.lastname;
        user.xname = this.xname;
        user.degree = this.degree;
        user.stateUser = DAOs.getStateUserDao().findByKey(this.status);
        user.getRoleInBusiness().add(new RoleInBusiness(DAOs.getTypeRoleInBusinessDao().findById(Long.valueOf(this.role))));
        user.email = this.email;
        user.phoneNumber = this.phonenumber;
        return user;
    }
}
