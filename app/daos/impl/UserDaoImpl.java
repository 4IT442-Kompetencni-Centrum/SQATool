package daos.impl;

import daos.UserDao;
import models.User;
import play.db.jpa.JPA;

/**
 * Created by Petr Kadlec on 29.3.2015.
 */
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao{

    UserDaoImpl(){

    }

    @Override
    public User getValidUser(String userName, String password) {
        return null; //TODO
    }
}
