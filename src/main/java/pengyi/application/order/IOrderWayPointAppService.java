package pengyi.application.order;

import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.core.api.BaseResponse;

import java.util.List;

/**
 * Created by pengyi on 2016/4/28.
 */
public interface IOrderWayPointAppService {

    List<OrderWayPointRepresentation> list(String orderId);

}
