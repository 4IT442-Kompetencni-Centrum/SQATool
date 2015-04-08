package daos;

import models.User;

/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public interface UserDao extends AbstractVersionedDao<User> {

    public User getValidUser(String userName, String password);
}
