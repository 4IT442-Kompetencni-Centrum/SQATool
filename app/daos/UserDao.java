package daos;

import models.User;

/**
 * Created by Petr Kadlec on 29.3.2015.
 */
public interface UserDao extends AbstractDao<User>{

    public User getValidUser(String userName, String password);
}
