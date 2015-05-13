package daos.impl;

import daos.RoleInBusinessDao;
import models.RoleInBusiness;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by petr on 11.5.15.
 */
public class RoleInBusinessDaoImpl extends AbstractVersionedDaoImpl<RoleInBusiness> implements RoleInBusinessDao{

    @Override
    public RoleInBusiness findById(Long id) {
        TypedQuery<RoleInBusiness> query = JPA.em().createQuery("SELECT role FROM RoleInBusiness role where role.id = :id", RoleInBusiness.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public RoleInBusiness findByRoleType(Long roleTypeId) {
        TypedQuery<RoleInBusiness> query = JPA.em().createQuery("SELECT role FROM RoleInBusiness role where role.roleInBusinessId = :roleTypeId", RoleInBusiness.class);
        query.setParameter("roleTypeId", roleTypeId);
        return query.getSingleResult();
    }

    @Override
    public void delete(RoleInBusiness role){
        Query query = JPA.em().createQuery("delete from RoleInBusiness r where r.id = :roleId");
        query.setParameter("roleId", role.getRoleInBusinessId());
        query.executeUpdate();
    }


}
