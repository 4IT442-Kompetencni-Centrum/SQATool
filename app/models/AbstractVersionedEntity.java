package models;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract class which is ancestor of all versioned entities in IS.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@MappedSuperclass
public abstract class AbstractVersionedEntity {
	
	@Version
	protected Integer version;
	protected Boolean visible;
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
