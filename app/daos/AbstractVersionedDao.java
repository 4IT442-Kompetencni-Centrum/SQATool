package daos;

import models.AbstractVersionedEntity;

/**
 * DAO interface which is ancestor of every DAO interface which work with Entites which extends AbstractVersionedEntity
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 * @param <T extends AbstractVersionedEntity>
 */
public interface AbstractVersionedDao<T extends AbstractVersionedEntity> extends AbstractDao<T> {

}
