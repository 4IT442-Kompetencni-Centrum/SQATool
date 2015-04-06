package daos.impl;

import java.util.List;

import javax.persistence.Query;

import models.Partner;
import play.db.jpa.JPA;
import daos.PartnerDao;

/**
 * Implementation of DAO for DB operations for Partner objects. 
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
public class PartnerDaoImpl extends AbstractVersionedDaoImpl<Partner> implements
		PartnerDao {
	PartnerDaoImpl() {
	
	}

	@Override
	public List<Partner> findByName(String name) {
		Query q = JPA.em().createQuery("SELECT p FROM Partner p WHERE p.name LIKE :pname AND p.visible = TRUE");
		q.setParameter("pname", "%"+name+"%");
		return q.getResultList();
	}
}
