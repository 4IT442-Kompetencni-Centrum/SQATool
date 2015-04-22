package daos.impl;

import models.User;
import daos.UserDao;
import play.db.jpa.JPA;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public class UserDaoImpl extends AbstractVersionedDaoImpl<User> implements UserDao {
   

    public User getValidUser(String username, String password){
        try{
            TypedQuery<User> query = JPA.em().createQuery("select user from User user where user.username = :username and user.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }catch(NoResultException exception){
            System.out.println("No result exception: " + exception.getMessage());
            return null;
        }
    }
}
