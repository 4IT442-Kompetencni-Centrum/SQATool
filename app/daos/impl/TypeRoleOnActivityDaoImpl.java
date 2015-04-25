package daos.impl;

import daos.TypeRoleOnActivityDao;
import models.TypeRoleOnActivity;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Implementation of DAO for DB operations for TypeRoleOnActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public class TypeRoleOnActivityDaoImpl extends AbstractNonVersionedDaoImpl<TypeRoleOnActivity>
        implements TypeRoleOnActivityDao {


    @Override
    public TypeRoleOnActivity findByKey(@NotNull String key) {
        TypedQuery<TypeRoleOnActivity> q = JPA.em().createQuery("SELECT tra FROM TypeRoleOnActivity tra where tra.key = :key", TypeRoleOnActivity.class);
        q.setParameter("key", key);
        return q.getSingleResult();
    }
}
