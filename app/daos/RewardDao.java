package daos;

import models.Reward;

import java.util.List;

/**
 * DAO for DB operations for Reward objects.
 *
 * @author Aleš Jiránek <a.jiranek@gmail.com>
 */
public interface RewardDao extends AbstractDao<Reward> {

    /**
     * Returns all rewards
     *
     * @param Integer offset - number of items to skip
     * @param Limit limit - max count of returned rewards
     * @return List<Reward>
     */
    public List<Reward> findAll(Integer offset, Integer limit);


    /**
     * Returns number of all rewards
     *
     * @return Integer
     */
    public Integer count();
}
