package daos;

import java.util.Map;

import models.User;

/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public interface UserDao extends AbstractVersionedDao<User> {

    public User getValidUser(String userName, String password);


    /**
     * Returns all users as map for select box
     * @return Map<String,String>
     */
    public Map<String,String> getUsersForSelectBox();
}
