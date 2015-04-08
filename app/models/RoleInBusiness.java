package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity RoleInBusiness
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
public class RoleInBusiness {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleInBusinessId;
	@ManyToOne
	private TypeRoleInBusiness typeRoleInBusiness;
	@ManyToOne
	private User user;
	
	public Long getRoleInBusinessId() {
		return roleInBusinessId;
	}
	public void setRoleInBusinessId(Long roleInBusinessId) {
		this.roleInBusinessId = roleInBusinessId;
	}
	public TypeRoleInBusiness getTypeRoleInBusiness() {
		return typeRoleInBusiness;
	}
	public void setTypeRoleInBusiness(TypeRoleInBusiness typeRoleInBusiness) {
		this.typeRoleInBusiness = typeRoleInBusiness;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}