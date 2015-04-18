package daos.impl;

import models.User;
import daos.UserDao;
import play.db.jpa.JPA;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Petr Kadlec on 8.4.2015.
 */
public class UserDaoImpl extends AbstractVersionedDaoImpl<User> implements UserDao {
   

    public User getValidUser(String username, String password){
        return null; // TODO
    }


    public Map<String, String> getUsersForSelectBox()
    {
        Query query = JPA.em().createQuery("SELECT u FROM User u WHERE u.visible = TRUE ORDER BY u.lastName");
        List<User> list = query.getResultList();

        HashMap<String, String> map = new HashMap<>();

        for(User user : list) {
            map.put(user.id.toString(), user.getLastName() + " " + user.getFirstName() +" <" + user.getEmail() + ">");
        }

        return map;
    }
}
