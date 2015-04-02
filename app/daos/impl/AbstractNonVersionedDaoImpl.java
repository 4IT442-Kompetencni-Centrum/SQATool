package daos.impl;

import play.db.jpa.JPA;
import daos.AbstractNonVersionedDao;

/**
 * Abstract DAO class which is ancestor of all DAOs which work with Enumerations.
 * 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @param <T>
 */
public class AbstractNonVersionedDaoImpl<T> extends AbstractDaoImpl<T> implements AbstractNonVersionedDao<T> {

	/**
	 * Method deletes row in database. 
	 */
	public void delete(T object) {
		JPA.em().remove(object);
	}

}
