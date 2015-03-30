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
        String queryString = "select * from SQA_USER where USERNAME like :username and PASSWORD like :password";
        TypedQuery<User> query = JPA.em().createQuery(queryString, User.class);
        query.setParameter("username", userName);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
