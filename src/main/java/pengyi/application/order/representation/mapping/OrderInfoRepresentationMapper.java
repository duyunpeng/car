package pengyi.application.order.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.order.representation.OrderInfoRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.order.Order;

/**
 * Created by YJH on 2016/4/12.
 */
@Component
public class OrderInfoRepresentationMapper extends CustomMapper<Order, OrderInfoRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Order order, OrderInfoRepresentation representation, MappingContext context) {
        if (null != order.getReceiveUser()) {
            DriverRepresentation data = mappingService.map(order.getReceiveUser(), DriverRepresentation.class, false);
            representation.setDriverId(data.getId());
            representation.setDriverUserName(data.getUserName());
            representation.setDriverName(data.getName());
            representation.setDriverHeadPic(data.getHead());
            representation.setDriverType(data.getDriverType());
        }
    }

}
