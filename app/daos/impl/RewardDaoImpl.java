package daos.impl;

import daos.RewardDao;
import models.Reward;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of DAO for DB operations for Reward objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class RewardDaoImpl extends AbstractVersionedDaoImpl<Reward>
        implements RewardDao {

    @Override
    public List<Reward> findAll(Integer offset, Integer limit) {

        Query query = JPA.em().createQuery("SELECT r FROM Reward r WHERE r.visible = TRUE");

        if(offset != 0)
            query.setFirstResult(offset);
        if(limit != 0)
            query.setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public Integer count() {
        TypedQuery<Long> q = JPA.em().createQuery("SELECT count(r) FROM Reward r WHERE r.visible = TRUE", Long.class);
        return q.getSingleResult().intValue();
    }
}
