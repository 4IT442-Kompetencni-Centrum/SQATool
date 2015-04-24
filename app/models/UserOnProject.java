package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity UserOnProject
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_USER_ON_PROJECT")
public class UserOnProject extends AbstractVersionedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userOnProjectId;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;
	@ManyToOne(fetch=FetchType.EAGER)
	private TypeUserOnProject typeUserOnProject;
	
	public Long getUserOnProjectId() {
		return userOnProjectId;
	}
	public void setUserOnProjectId(Long userOnProjectId) {
		this.userOnProjectId = userOnProjectId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public TypeUserOnProject getTypeUserOnProject() {
		return typeUserOnProject;
	}
	public void setTypeUserOnProject(TypeUserOnProject typeUserOnProject) {
		this.typeUserOnProject = typeUserOnProject;
	}

	

}
