package pengyi.repository.billing;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.model.billing.IBillingRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/21.
 */
@Repository("billingRepository")
public class BillingRepository extends AbstractHibernateGenericRepository<Billing, String>
        implements IBillingRepository<Billing, String> {
    @Override
    public Billing searchByCompany(String id) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("company.id", id));
        return (Billing) criteria.uniqueResult();
    }

    @Override
    public Billing searchUnique(DriverType driverType, CarType carType, String companyId) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("company.id", companyId));
        criteria.add(Restrictions.eq("driverType", driverType));
        if (null != carType) {
            criteria.add(Restrictions.eq("carType", carType));
        }
        return (Billing) criteria.uniqueResult();
    }
}
