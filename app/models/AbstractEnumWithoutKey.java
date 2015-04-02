package models;

import javax.persistence.MappedSuperclass;

/**
 * Abstract class for storing enumerations. This class has no key attribute.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@MappedSuperclass
public abstract class AbstractEnumWithoutKey {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
