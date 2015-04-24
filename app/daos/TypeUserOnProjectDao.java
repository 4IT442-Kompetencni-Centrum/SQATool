package daos;

import javax.validation.constraints.NotNull;

import models.TypeUserOnProject;

public interface TypeUserOnProjectDao extends AbstractNonVersionedDao<TypeUserOnProject> {
	
	/**
     * Returns user on project type with given key
     *
     * @param String key
     * @return TypeActivity
     */
    public TypeUserOnProject findByKey(@NotNull String key);
}
