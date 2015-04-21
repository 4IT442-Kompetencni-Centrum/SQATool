package daos.impl;

import daos.UserLoggedOnActivityDao;
import models.Activity;
import models.TypeRoleOnActivity;
import models.User;
import models.UserLoggedOnActivity;
import play.db.jpa.JPA;
import service.EnumerationWithKeys;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of DAO for DB operations for UserLoggedOnActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class UserLoggedOnActivityDaoImpl extends AbstractNonVersionedDaoImpl<UserLoggedOnActivity>
        implements UserLoggedOnActivityDao {

    @Override
    public UserLoggedOnActivity find(Activity activity, User user, TypeRoleOnActivity role) {

        TypedQuery<UserLoggedOnActivity> q = JPA.em().createQuery("SELECT ula FROM UserLoggedOnActivity ula WHERE ula.activity = :activity AND ula.user = :user AND ula.typeRoleOnActivity = :role", UserLoggedOnActivity.class);
        q.setParameter("activity", activity);
        q.setParameter("user", user);
        q.setParameter("role", role);


        return q.getSingleResult();
    }

    @Override
    public List<User> getLoggedUsers(Activity activity) {
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);

        TypedQuery<User> q = JPA.em().createQuery("SELECT u FROM UserLoggedOnActivity ula JOIN ula.user u WHERE ula.activity = :activity AND ula.typeRoleOnActivity = :role", User.class);
        q.setParameter("activity", activity);
        q.setParameter("role", role);

        return q.getResultList();
    }

    @Override
    public User getOrganizer(Activity activity) {
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_ORGANIZER);

        TypedQuery<User> q = JPA.em().createQuery("SELECT u FROM UserLoggedOnActivity ula JOIN ula.user u WHERE ula.activity = :activity AND ula.typeRoleOnActivity = :role", User.class);
        q.setParameter("activity", activity);
        q.setParameter("role", role);

        return q.getSingleResult();
    }

    @Override
    public Boolean isLogged(Activity activity, User user) {
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);

        TypedQuery<Long> q = JPA.em().createQuery("SELECT count(ula) FROM UserLoggedOnActivity ula WHERE ula.activity = :activity AND ula.typeRoleOnActivity = :role AND ula.user = :user", Long.class);
        q.setParameter("activity", activity);
        q.setParameter("role", role);
        q.setParameter("user", user);

        return q.getSingleResult().intValue() != 0;
    }
}
