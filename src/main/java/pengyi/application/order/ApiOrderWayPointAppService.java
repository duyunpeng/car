package pengyi.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.order.command.UploadWayCommand;
import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.service.order.IOrderWayPointService;

/**
 * Created by pengyi on 2016/4/28.
 */
@Service("apiOrderWayPointAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiOrderWayPointAppService implements IApiOrderWayPointAppService {

    @Autowired
    private IOrderWayPointService orderWayPointService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public BaseResponse list(String orderId) {
        if (CoreStringUtils.isEmpty(orderId)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0,
                mappingService.mapAsList(orderWayPointService.list(orderId), OrderWayPointRepresentation.class),
                ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse upload(UploadWayCommand command) {

        if (CoreStringUtils.isEmpty(command.getOrderId())) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        if (0 == command.getLat() || 0 == command.getLon()) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10037.getMessage());
        }
        orderWayPointService.upload(command);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }
}
