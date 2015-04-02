package daos.impl;

import models.Partner;
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
}
