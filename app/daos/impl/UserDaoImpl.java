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
   
    @Override
    public User getValidUser(String username, String password){
        try{
            TypedQuery<User> query = JPA.em().createQuery("select user from User user where user.username = :username and user.password = :password and user.visible = true", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }catch(NoResultException exception){
            System.out.println("No result exception: " + exception.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByUserName(String username){
        TypedQuery<User> query = JPA.em().createQuery("select user from User user where user.username = :username and user.visible = true ", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

	@Override
	public List<User> getUsersByQuery(String queryString) {
		Query query = JPA.em().createQuery("SELECT u from User u WHERE upper(u.firstName || ' ' || u.lastName) LIKE upper(:queryString) AND u.visible = TRUE ORDER BY u.lastName").setMaxResults(10);
		query.setParameter("queryString", "%"+queryString+"%");
		return query.getResultList();
	}

    @Override
    public List<User> getAllMembers(Integer offset, Integer limit){
        Query query = JPA.em().createQuery("SELECT u from User u where u.visible = TRUE");
        if(offset != 0) query.setFirstResult(offset);
        if(limit != 0) query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<User> getAllMembers(){
        Query query = JPA.em().createQuery("SELECT user from User user");
        return query.getResultList();
    }

    @Override
    public Integer getNumberOfMembers(){
        TypedQuery<Long> query = JPA.em().createQuery("SELECT count(u) FROM User u WHERE u.visible = TRUE", Long.class);
        return query.getSingleResult().intValue();
    }


}
