package pengyi.domain.model.order;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IOrderRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    Order byOrderNumber(String orderNumber);
}
