package daos;

import models.TypeActivity;

import java.util.HashMap;
import java.util.List;

/**
 * DAO for DB operations for TypeActivity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface TypeActivityDao extends AbstractNonVersionedDao<TypeActivity> {

    /**
     * Returns hash map for select box
     *
     * @return HashMap<String,String>
     */
    public HashMap<String,String> getOptions();

    public List<TypeActivity> findAll();


}
