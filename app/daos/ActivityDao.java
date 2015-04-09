package daos;

import models.Activity;

import java.util.List;

/**
 * DAO for DB operations for Activity objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface ActivityDao extends AbstractVersionedDao<Activity> {
    /**
     * Returns all activities
     *
     * @param Integer offset - number of items to skip
     * @param Limit limit - max count of returned rewards
     * @return List<Reward>
     */
    public List<Activity> findAll(Integer offset, Integer limit);


    /**
     * Returns count of all activities
     *
     * @return Integer
     */
    public Integer count();

}
