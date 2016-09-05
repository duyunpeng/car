package pengyi.repository.permission;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.permission.IPermissionRepository;
import pengyi.domain.model.permission.Permission;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("permissionRepository")
public class PermissionRepository extends AbstractHibernateGenericRepository<Permission, String>
        implements IPermissionRepository<Permission, String> {
    @Override
    public Permission getByName(String permissionName) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("permissionName",permissionName));
        return (Permission) criteria.uniqueResult();
    }
}
