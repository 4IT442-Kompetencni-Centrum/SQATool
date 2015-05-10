package views.formData;

import daos.impl.DAOs;
import models.RoleInBusiness;
import models.User;
import play.data.validation.Constraints;

/**
 * Created by petr on 7.5.15.
 */
public class NewMemberForm {

    public Long id;
    @Constraints.Required
    @Constraints.MaxLength(value= 200, message="Zadané jméno je příliš dlouhé")
    public String firstname;
    @Constraints.Required
    @Constraints.MaxLength(value= 200, message="Zadané příjmení je příliš dlouhé")
    public String lastname;
    public String xname;
    public String degree;
    @Constraints.Required
    public String status;
    public String role;
    @Constraints.Required
    @Constraints.Email(message = "Neplatný email")
    public String email;
    @Constraints.Pattern(message = "Neplatné telefonní číslo", value = "^(\\+420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$")
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
