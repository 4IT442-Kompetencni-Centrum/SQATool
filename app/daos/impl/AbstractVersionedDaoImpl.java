package daos.impl;

import play.db.jpa.JPA;
import models.AbstractVersionedEntity;
import daos.AbstractVersionedDao;

/**
 * Abstract DAO class which is ancestor of all DAOs which work with Entities which extends AbstractVersionedEntity
 * 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @param <T>
 */
public abstract class AbstractVersionedDaoImpl<T extends AbstractVersionedEntity> extends AbstractDaoImpl<T> implements AbstractVersionedDao<T> {
	
	/**
	 * Method deletes row in database. Delete is implemented as set of visible
	 * flag to false.
	 */
	public void delete(T object) {
		object.setVisible(false);
		update(object);
	}
	
	@Override
	public T findById(Long id) {
		T res = JPA.em().find(entityClass, id);
		if (res.getVisible() == null || !res.getVisible()) {
			return null;
		}
		return res;
	}
	
}
