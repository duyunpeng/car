package pengyi.application.order;

import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IOrderAppService {

    Pagination<OrderRepresentation> pagination(ListOrderCommand command);

    OrderRepresentation show(String id);

    List<OrderRepresentation> exportExcel(ListOrderCommand command);

    OrderRepresentation byOrderNumber(String message);
}
