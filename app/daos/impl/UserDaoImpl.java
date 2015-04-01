package daos.impl;

import daos.UserDao;
import models.User;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;

/**
 * Created by Petr Kadlec on 29.3.2015.
 */
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao{

    UserDaoImpl(){

    }

    @Override
    public User getValidUser(String userName, String password) {
        TypedQuery<User> query = JPA.em().createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", userName);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
