package daos;

import java.util.List;

import models.ContactPerson;
import models.Partner;

/**
 * DAO for DB operations for ContactPerson objects.
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public interface ContactPersonDao extends AbstractVersionedDao<ContactPerson>{
	/**
	 * Method returns list of ContactPerson for given partner
	 * @param partner
	 * @return
	 */
	public List<ContactPerson> getContactPersonForPartner(Partner partner);
}
