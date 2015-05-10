package daos;

import models.TypeRoleInBusiness;

import java.util.List;

public interface TypeRoleInBusinessDao extends AbstractNonVersionedDao<TypeRoleInBusiness> {

    public List<TypeRoleInBusiness> findAll();

}
