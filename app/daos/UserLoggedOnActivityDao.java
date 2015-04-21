package daos;

import models.Activity;
import models.TypeRoleOnActivity;
import models.User;
import models.UserLoggedOnActivity;

import java.util.List;

/**
 * DAO for DB operations for UserLoggedOnActivity objects.
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 *
 */
public interface UserLoggedOnActivityDao extends AbstractNonVersionedDao<UserLoggedOnActivity> {

    /**
     * Returns UserLoggedOnActivity object for given parameters
     *
     * @param Activity activity
     * @param User userId
     * @return UserLoggedOnActivity
     */
    public UserLoggedOnActivity find(Activity activity, User user, TypeRoleOnActivity role);


    /**
     * Returns users logged on activity with given role
     * @param activity
     * @param role
     * @return
     */
    public List<User> getLoggedUsers(Activity activity);


    /**
     * Returns activity organizer
     * @param activity
     * @return
     */
    public User getOrganizer(Activity activity);


    /**
     * Returns true if user is logged on activity
     * @param activity
     * @param user
     * @return
     */
    public Boolean isLogged(Activity activity, User user);
}
