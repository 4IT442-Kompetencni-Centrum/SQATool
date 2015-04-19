package daos;

import models.StateUser;

/**
 * Created by Petr Kadlec on 18.4.2015.
 */
public interface StateUserDao extends AbstractNonVersionedDao<StateUser>{
    public StateUser findByKey(String key);
}
