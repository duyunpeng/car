package pengyi.application.order.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.domain.model.order.OrderWayPoint;

/**
 * Created by pengyi on 2016/4/28.
 */
@Component
public class OrderWayPointRepresentationMapper extends CustomMapper<OrderWayPoint, OrderWayPointRepresentation> {
}
