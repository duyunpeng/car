package pengyi.repository.user.driver;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("driverRepository")
public class DriverRepository extends AbstractHibernateGenericRepository<Driver, String>
        implements IDriverRepository<Driver, String> {
    @Override
    public List<Driver> searchByCompany(String company) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("company.id", company));
        return criteria.list();
    }

    @Override
    public Driver searchByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (Driver) criteria.uniqueResult();
    }
}
