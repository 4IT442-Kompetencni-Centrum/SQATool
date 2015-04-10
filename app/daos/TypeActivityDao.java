package daos;

import models.TypeActivity;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * DAO for DB operations for TypeActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface TypeActivityDao extends AbstractNonVersionedDao<TypeActivity> {

    /**
     * Returns activity type with given key
     *
     * @param String key
     * @return TypeActivity
     */
    public TypeActivity findByKey(@NotNull String key);


    /**
     * Returns hash map for select box
     *
     * @return HashMap<String,String>
     */
    public HashMap<String,String> getOptions();

}