package pengyi.repository.order;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.order.IOrderRepository;
import pengyi.domain.model.order.Order;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/8.
 */
@Repository("orderRepository")
public class OrderRepository extends AbstractHibernateGenericRepository<Order, String>
        implements IOrderRepository<Order, String> {

    @Override
    public Order byOrderNumber(String orderNumber) {

        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("orderNumber",orderNumber));
        return (Order) criteria.uniqueResult();
    }
}
