package daos.impl;

import models.User;
import daos.UserDao;

/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public class UserDaoImpl extends AbstractVersionedDaoImpl<User> implements UserDao {
   

    public User getValidUser(String username, String password){
        return null; // TODO
    }
}
