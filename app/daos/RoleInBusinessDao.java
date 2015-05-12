package daos;

import models.RoleInBusiness;

/**
 * Created by petr on 11.5.15.
 */
public interface RoleInBusinessDao extends AbstractVersionedDao<RoleInBusiness>  {

    public RoleInBusiness findById(Long id);

    public RoleInBusiness findByRoleType(Long roleTypeId);
}
