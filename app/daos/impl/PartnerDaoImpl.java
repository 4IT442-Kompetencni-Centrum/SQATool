package daos.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Partner;
import play.Logger;
import play.db.jpa.JPA;
import service.Configuration;
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

	@Override
	public List<Partner> getAllPartners(Integer start, Integer limit) {
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = Configuration.PAGE_SIZE;
				Logger.debug("No limit was given to getAllPartners. {} is now set as limit.", Configuration.PAGE_SIZE);
			}
			Query query = JPA.em().createQuery("SELECT p FROM Partner p WHERE p.visible = TRUE ORDER BY p.name").setMaxResults(limit).setFirstResult(start);
			return query.getResultList();
	}

	@Override
	public Integer getNumberOfPartners() {
		TypedQuery<Long> q = JPA.em().createQuery("SELECT count(p) FROM Partner p WHERE p.visible = TRUE", Long.class);
		return q.getSingleResult().intValue();
	}
}
