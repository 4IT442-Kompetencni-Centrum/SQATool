package daos.impl;

import java.util.List;

import javax.persistence.Query;

import play.db.jpa.JPA;
import daos.ContactPersonDao;
import models.ContactPerson;
import models.Partner;

/**
 * Implementation of DAO for DB operations for ContactPerson objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class ContactPersonDaoImpl extends AbstractVersionedDaoImpl<ContactPerson> implements ContactPersonDao {

	ContactPersonDaoImpl() {
		
	}

	@Override
	public List<ContactPerson> getContactPersonForPartner(Partner partner) {
		Query q = JPA.em().createQuery("SELECT cp from ContactPerson cp WHERE cp.partner = :partner");
		q.setParameter("partner", partner);
		return q.getResultList();
	}
	
}
