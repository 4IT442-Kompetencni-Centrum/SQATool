package daos;

import java.util.List;

import models.User;

/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public interface UserDao extends AbstractVersionedDao<User> {

    public User getValidUser(String userName, String password);
    public List<User> getUsersByQuery(String query);
    public List<User> getAllMembers(Integer offset, Integer limit);

}
