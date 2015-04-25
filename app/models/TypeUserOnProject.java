package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SQA_TYPE_USER_ON_PROJECT")
public class TypeUserOnProject extends AbstractEnumWithKey {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long typeUserOnProjectId;

	public Long getTypeUserOnProjectId() {
		return typeUserOnProjectId;
	}

	public void setTypeUserOnProjectId(Long typeUserOnProjectId) {
		this.typeUserOnProjectId = typeUserOnProjectId;
	}
	
	
}
