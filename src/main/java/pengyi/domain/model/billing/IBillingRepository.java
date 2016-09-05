package pengyi.domain.model.billing;

import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IBillingRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Billing searchByCompany(String id);

    Billing searchUnique(DriverType driverType, CarType carType, String companyId);
}
