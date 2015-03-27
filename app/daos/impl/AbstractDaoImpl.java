package daos.impl;

import java.lang.reflect.ParameterizedType;

import daos.AbstractDao;
import models.AbstractVersionedEntity;
import play.db.jpa.JPA;

/**
 * Abstract class which is ancestor of all DAOs.
 * 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @param <T>
 */
public abstract class AbstractDaoImpl<T extends AbstractVersionedEntity>
		implements AbstractDao<T> {

	protected Class<T> entityClass;

	/**
	 * Constructor
	 */
	public AbstractDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	/**
	 * Method creates new row in database.
	 */
	public T create(T object) {
		JPA.em().persist(object);
		return object;
	}

	/**
	 * Method updates row in database.
	 */
	public T update(T object) {
		return JPA.em().merge(object);
	}

	/**
	 * Method deletes row in database. Delete is implemented as set of visible
	 * flag to false.
	 */
	public void delete(T object) {
		object.setVisible(false);
		update(object);
	}

	/**
	 * Method finds row in db with given id
	 * 
	 * @param Long
	 *            id
	 */
	public T findById(Long id) {
		return JPA.em().find(entityClass, id);
	}

}
