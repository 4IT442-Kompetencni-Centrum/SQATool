package daos;

import models.TypeRoleOnActivity;

import javax.validation.constraints.NotNull;

/**
 * DAO for DB operations for TypeRoleOnActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface TypeRoleOnActivityDao extends AbstractNonVersionedDao<TypeRoleOnActivity> {
    /**
     * Returns activity type with given key
     *
     * @param String key
     * @return TypeActivity
     */
    public TypeRoleOnActivity findByKey(@NotNull String key);
}
