package daos.impl;

import daos.StateUserDao;
import models.StateUser;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Created by Petr Kadlec on 18.4.2015.
 */
public class StateUserDaoImpl extends AbstractNonVersionedDaoImpl<StateUser> implements StateUserDao{

    @Override
    public StateUser findByKey(@NotNull String key) {
        TypedQuery<StateUser> q = JPA.em().createQuery("SELECT sp FROM StateUser sp where sp.key = :key", StateUser.class);
        q.setParameter("key", key);
        return q.getSingleResult();
    }
}
