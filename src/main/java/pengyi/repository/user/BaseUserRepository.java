package pengyi.repository.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IBaseUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("baseUserRepository")
public class BaseUserRepository extends AbstractHibernateGenericRepository<BaseUser, String> implements IBaseUserRepository<BaseUser, String> {
    @Override
    public BaseUser getByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (BaseUser) criteria.uniqueResult();
    }
}