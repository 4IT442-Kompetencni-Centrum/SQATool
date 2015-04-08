package daos;

import java.util.List;

import models.Partner;

/**
 * DAO for DB operations for Partner objects.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public interface PartnerDao extends AbstractVersionedDao<Partner> {
	public List<Partner> findByName(String name);
}
