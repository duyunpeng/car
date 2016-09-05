package pengyi.repository.order;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.order.IOrderWayPointRepository;
import pengyi.domain.model.order.OrderWayPoint;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by pengyi on 2016/4/27.
 */
@Repository("orderWayPointRepository")
public class OrderWayPointRepository extends AbstractHibernateGenericRepository<OrderWayPoint, String>
        implements IOrderWayPointRepository<OrderWayPoint, String> {

}
