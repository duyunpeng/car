package pengyi.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.order.Order;
import pengyi.domain.service.order.IOrderService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
@Service("orderAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderAppService implements IOrderAppService {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<OrderRepresentation> pagination(ListOrderCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Order> pagination = orderService.pagination(command);
        List<OrderRepresentation> data = mappingService.mapAsList(pagination.getData(), OrderRepresentation.class);
        return new Pagination<OrderRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRepresentation show(String id) {
        return mappingService.map(orderService.show(id), OrderRepresentation.class, false);
    }

    @Override
    public List<OrderRepresentation> exportExcel(ListOrderCommand command) {
        return mappingService.mapAsList(orderService.exportExcel(command), OrderRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRepresentation byOrderNumber(String orderNumber) {
        return mappingService.map(orderService.byOrderNumber(orderNumber), OrderRepresentation.class, false);
    }
}
