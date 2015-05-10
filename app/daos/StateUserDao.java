package daos;

import models.StateUser;

import java.util.List;

/**
 * Created by Petr Kadlec on 18.4.2015.
 */
public interface StateUserDao extends AbstractNonVersionedDao<StateUser>{
    public StateUser findByKey(String key);
    public List<StateUser> findAll();
}
