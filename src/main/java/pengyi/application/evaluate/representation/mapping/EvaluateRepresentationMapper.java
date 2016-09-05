package pengyi.application.evaluate.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.evaluate.Evaluate;


/**
 * Created by lvdi on 2016/3/7.
 */
@Component
public class EvaluateRepresentationMapper extends CustomMapper<Evaluate, EvaluateRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Evaluate evaluate, EvaluateRepresentation representation, MappingContext context) {
        if (null != evaluate.getOrder()) {
            OrderRepresentation order = mappingService.map(evaluate.getOrder(), OrderRepresentation.class, false);
            if (null != evaluate.getOrder().getReceiveUser()) {
                DriverRepresentation data = mappingService.map(evaluate.getOrder().getReceiveUser(), DriverRepresentation.class, false);
                order.setReceiveUser(data);
            }
            representation.setOrder(order);
        }
        if (null != evaluate.getEvaluateUser()) {
            BaseUserRepresentation evaluateUser = mappingService.map(evaluate.getEvaluateUser(), BaseUserRepresentation.class, false);
            representation.setEvaluateUser(evaluateUser);
        }
    }
}
