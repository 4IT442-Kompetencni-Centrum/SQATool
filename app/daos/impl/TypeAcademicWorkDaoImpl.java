package daos.impl;

import daos.TypeAcademicWorkDao;
import daos.TypeActivityDao;
import models.TypeAcademicWork;
import models.TypeActivity;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of DAO for DB operations for TypeAcademicWork objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class TypeAcademicWorkDaoImpl extends AbstractNonVersionedDaoImpl<TypeAcademicWork>
        implements TypeAcademicWorkDao {

    public TypeAcademicWork findByKey(@NotNull String key) {
        TypedQuery<TypeAcademicWork> q = JPA.em().createQuery("SELECT taw FROM TypeAcademicWork taw where taw.key = :key", TypeAcademicWork.class);
        q.setParameter("key", key);
        return q.getSingleResult();
    }


    @Override
    public HashMap<String, String> getOptions() {
        HashMap<String,String> options = new HashMap<>();

        Query query = JPA.em().createQuery("SELECT taw FROM TypeAcademicWork taw ORDER BY taw.key");
        List<TypeAcademicWork> types = query.getResultList();

        for(TypeAcademicWork typeAcademicWork : types) {
            options.put(typeAcademicWork.getKey(),typeAcademicWork.getValue());
        }

        return options;
    }
}
