package daos.impl;

import models.TypeRoleInBusiness;
import daos.TypeRoleInBusinessDao;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

public class TypeRoleInBusinessDaoImpl extends AbstractNonVersionedDaoImpl<TypeRoleInBusiness> implements TypeRoleInBusinessDao {

    @Override
    public List<TypeRoleInBusiness> findAll(){
        Query query = JPA.em().createQuery("SELECT role from TypeRoleInBusiness role");
        return query.getResultList();
    }
}
