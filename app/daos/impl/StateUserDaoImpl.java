package daos.impl;

import daos.StateUserDao;
import models.StateUser;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Petr Kadlec on 18.4.2015.
 */
public class StateUserDaoImpl extends AbstractNonVersionedDaoImpl<StateUser> implements StateUserDao{

    @Override
    public StateUser findById(Long id){
        TypedQuery<StateUser> query = JPA.em().createQuery("SELECT state from StateUser state where state.id = :id", StateUser.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public StateUser findByKey(@NotNull String key) {
        TypedQuery<StateUser> q = JPA.em().createQuery("SELECT sp FROM StateUser sp where sp.key = :key", StateUser.class);
        q.setParameter("key", key);
        return q.getSingleResult();
    }

    @Override
    public List<StateUser> findAll(){
        Query query = JPA.em().createQuery("SELECT state FROM StateUser state");
        return query.getResultList();
    }
}
