package pengyi.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.service.order.IOrderWayPointService;

import java.util.List;

/**
 * Created by pengyi on 2016/4/28.
 */
@Service("orderWayPointAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class OrderWayPointAppService implements IOrderWayPointAppService {

    @Autowired
    private IOrderWayPointService orderWayPointService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public List<OrderWayPointRepresentation> list(String orderId) {
        return mappingService.mapAsList(orderWayPointService.list(orderId), OrderWayPointRepresentation.class);
    }
}
