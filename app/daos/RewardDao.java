package daos;

import models.Reward;
import models.User;

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


    /**
     * Returns users rewards
     *
     * @param user
     * @param offset
     * @param limit
     * @return
     */
    public List<Reward> findByUser(User user, Integer offset, Integer limit);


    /**
     * Returns number of all users rewards
     *
     * @return Integer
     */
    public Integer countUsersRewards(User user);
}
