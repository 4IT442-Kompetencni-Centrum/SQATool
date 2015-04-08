package daos.impl;

import daos.UserDao;
import models.User;

/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    public User getValidUser(String username, String password){
        return null; // TODO
    }
}
