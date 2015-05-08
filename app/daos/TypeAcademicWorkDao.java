package daos;

import models.TypeAcademicWork;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * DAO for DB operations for TypeAcademicWork objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface TypeAcademicWorkDao extends AbstractNonVersionedDao<TypeAcademicWork> {

    /**
     * Returns activity type with given key
     *
     * @param String key
     * @return TypeActivity
     */
    public TypeAcademicWork findByKey(@NotNull String key);


    /**
     * Returns hash map for select box
     *
     * @return HashMap<String,String>
     */
    public HashMap<String,String> getOptions();

}
