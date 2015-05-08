package daos.impl;

import daos.TypeActivityDao;
import models.StateProject;
import models.TypeActivity;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of DAO for DB operations for TypeActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class TypeActivityDaoImpl extends AbstractNonVersionedDaoImpl<TypeActivity>
        implements TypeActivityDao {

    @Override
    public HashMap<String, String> getOptions() {
        HashMap<String,String> options = new HashMap<>();

        Query query = JPA.em().createQuery("SELECT ta FROM TypeActivity ta ORDER BY ta.typeActivityId");
        List<TypeActivity> types = query.getResultList();

        for(TypeActivity typeActivity : types) {
            options.put(typeActivity.getTypeActivityId().toString(),typeActivity.getValue());
        }

        return options;
    }

    @Override
    public List<TypeActivity> findAll() {
        TypedQuery<TypeActivity> query = JPA.em().createQuery("SELECT ta FROM TypeActivity as ta", TypeActivity.class) ;
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            Logger.debug("No result was found for Activity");
            return null;
        }
    }
}
