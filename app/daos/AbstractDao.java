package daos;

/**
 * DAO interface which is ancestor of every DAO interface
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @param <T>
 */
public interface AbstractDao<T> {
	/**
	 * Method creates new row in db.
	 * @param object
	 * @return object
	 */
	public T create(T object);
	/**
	 * Method updates row in database
	 * @param object
	 * @return object
	 */
	public T update(T object);
	/**
	 * Method deletes row in db. Delete operation is implemented as setting visible flag to false.
	 * @param object
	 */
	public void delete(T object);
	/**
	 * Method find row with given id.
	 * @param id
	 * @return
	 */
	public T findById(Long id);
}
