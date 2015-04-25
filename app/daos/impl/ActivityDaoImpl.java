package daos.impl;

import daos.ActivityDao;
import models.Activity;
import models.UserLoggedOnActivity;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of DAO for DB operations for Activity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class ActivityDaoImpl extends AbstractVersionedDaoImpl<Activity>
        implements ActivityDao {

    @Override
    public List<Activity> findAll(Integer offset, Integer limit) {
        Query query = JPA.em().createQuery("SELECT a FROM Activity a WHERE a.visible = TRUE");

        if(offset != 0)
            query.setFirstResult(offset);
        if(limit != 0)
            query.setMaxResults(limit);

        return query.getResultList();

    }


    @Override
    public Integer count() {
        TypedQuery<Long> q = JPA.em().createQuery("SELECT count(a) FROM Activity a WHERE a.visible = TRUE", Long.class);
        return q.getSingleResult().intValue();
    }

}
