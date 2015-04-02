package models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class for storing enumerations. This class has key attribute which can be use for finding objects in db.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@MappedSuperclass
public abstract class AbstractEnumWithKey {
	
	@Column(unique=true)
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
