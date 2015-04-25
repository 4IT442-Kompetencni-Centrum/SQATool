package daos.impl;

import daos.UserLoggedOnActivityDao;
import models.Activity;
import models.TypeRoleOnActivity;
import models.User;
import models.UserLoggedOnActivity;
import play.db.jpa.JPA;
import service.EnumerationWithKeys;

import javax.persistence.Query;
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

    @Override
    public List<Activity> findByUser(User user, Integer offset, Integer limit) {
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);

        Query q = JPA.em().createQuery("SELECT a FROM UserLoggedOnActivity ula JOIN ula.activity a WHERE a.visible = TRUE AND ula.typeRoleOnActivity = :role AND ula.user = :user");
        q.setParameter("role", role);
        q.setParameter("user", user);

        if (offset != 0)
            q.setFirstResult(offset);
        if (limit != 0)
            q.setMaxResults(limit);

        return q.getResultList();
    }

    @Override
    public Integer countUserActivities(User user) {
        TypeRoleOnActivity role = DAOs.getTypeRoleOnActivityDao().findByKey(EnumerationWithKeys.TYPE_ROLE_ON_ACTIVITY_LOGGED);

        TypedQuery<Long> q = JPA.em().createQuery("SELECT count(ula) FROM UserLoggedOnActivity ula JOIN ula.activity a WHERE a.visible = TRUE AND ula.typeRoleOnActivity = :role AND ula.user = :user", Long.class);
        q.setParameter("role", role);
        q.setParameter("user", user);

        return q.getSingleResult().intValue();
    }
}
