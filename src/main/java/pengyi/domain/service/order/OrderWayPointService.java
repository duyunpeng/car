package pengyi.domain.service.order;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.order.command.UploadWayCommand;
import pengyi.domain.model.order.IOrderWayPointRepository;
import pengyi.domain.model.order.OrderWayPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/4/28.
 */
@Service("orderWayPointService")
public class OrderWayPointService implements IOrderWayPointService {

    @Autowired
    private IOrderWayPointRepository<OrderWayPoint, String> orderWayPointRepository;
    @Autowired
    private IOrderService orderService;

    @Override
    public List<OrderWayPoint> list(String orderId) {

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("order.id", orderId));

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("uploadTime"));

        return orderWayPointRepository.list(criterionList, orderList);
    }

    @Override
    public void upload(UploadWayCommand command) {
        pengyi.domain.model.order.Order order = orderService.show(command.getOrderId());
        OrderWayPoint point = new OrderWayPoint(order, command.getLat(), command.getLon(), new Date());
        orderWayPointRepository.save(point);
    }
}
