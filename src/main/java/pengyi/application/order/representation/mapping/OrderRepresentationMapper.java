package pengyi.application.order.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.order.Order;

/**
 * Created by YJH on 2016/3/8.
 */
@Component
public class OrderRepresentationMapper extends CustomMapper<Order, OrderRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Order order, OrderRepresentation representation, MappingContext context) {
        if (null != order.getOrderUser()) {
            BaseUserRepresentation data = mappingService.map(order.getOrderUser(), BaseUserRepresentation.class, false);
            representation.setOrderUser(data);
        }
        if (null != order.getReceiveUser()) {
            BaseUserRepresentation data = mappingService.map(order.getReceiveUser(), BaseUserRepresentation.class, false);
            representation.setReceiveUser(data);
        }
        if(null != order.getCreateDate()){
            String date = CoreDateUtils.formatDate(order.getCreateDate(),CoreDateUtils.DATETIME);
            representation.setCreateDate(date);
        }
    }

}
