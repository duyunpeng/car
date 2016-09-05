package pengyi.repository.role;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.role.IRoleRepository;
import pengyi.domain.model.role.Role;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("roleRepository")
public class RoleRepository extends AbstractHibernateGenericRepository<Role, String>
        implements IRoleRepository<Role, String> {
    @Override
    public Role getByName(String name) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("roleName",name));
        return (Role) criteria.uniqueResult();
    }
}
