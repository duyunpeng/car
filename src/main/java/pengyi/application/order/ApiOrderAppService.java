package pengyi.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.order.command.*;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.DriverType;
import pengyi.core.type.OrderStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.order.Order;
import pengyi.domain.service.order.IOrderService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiOrderAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiOrderAppService implements IApiOrderAppService {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse waitOrderPagination(ListOrderCommand command) {
        if (null != command) {
            command.verifyPage();
            command.verifyPageSize(10);
            command.setOrderStatus(OrderStatus.WAIT_ORDER);
            Pagination<Order> pagination = orderService.apiPagination(command);
            List<OrderRepresentation> data = mappingService.mapAsList(pagination.getData(), OrderRepresentation.class);

            Pagination<OrderRepresentation> result = new Pagination<OrderRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse companyOrderPagination(CompanyOrderListCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            Pagination<Order> pagination = orderService.apiCompanyOrderPagination(command);
            List<OrderRepresentation> data = mappingService.mapAsList(pagination.getData(), OrderRepresentation.class);

            Pagination<OrderRepresentation> result = new Pagination<OrderRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse createOrder(CreateOrderCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderUser())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10020.getMessage());
            }
            if (0 == command.getStartLat() || 0 == command.getStartLon() || 0 == command.getEndLat() || 0 == command.getEndLon()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10037.getMessage());
            }
            if (!CoreStringUtils.isEmpty(command.getSubscribeDate())) {
                if (null == CoreDateUtils.parseDate(command.getSubscribeDate(), CoreDateUtils.DATETIME)) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10022.getMessage());
                }
            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                if (null == command.getCarType()) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30002.getMessage());
                }
            }
            if (CoreStringUtils.isEmpty(command.getStartAddress())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10027.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getEndAddress())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10028.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getDrivers())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10034.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.apiCreateOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, order, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse receiveOrder(ReceiveOrderCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getReceiveUser())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10023.getMessage());
            }
            if (orderService.hasOrder(command.getReceiveUser(), command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_HAS_ORDER, 0, null, ResponseCode.RESPONSE_CODE_HAS_ORDER.getMessage());
            }
            if (!orderService.hasBalance(command.getReceiveUser())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS, 0, null, ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.apiReceiverOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse startOrder(UpDateOrderStatusCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.apiStartOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse show(String orderId) {
        if (CoreStringUtils.isEmpty(orderId)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
        }
        OrderRepresentation order = mappingService.map(orderService.show(orderId), OrderRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, order, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse waitPayOrder(UpDateOrderStatusCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (null == command.getKm()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10036.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.apiWaitPayOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

//    @Override
//    public BaseResponse payOrder(UpDateOrderStatusCommand command) {
//        if (null != command) {
//            if (CoreStringUtils.isEmpty(command.getOrderId())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
//            }
//            if (null == command.getVersion()) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
//            }
//            OrderRepresentation order = mappingService.map(orderService.apiPayOrder(command), OrderRepresentation.class, false);
//            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
//        } else {
//            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
//                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
//        }
//    }

    @Override
    public BaseResponse cancelOrder(UpDateOrderStatusCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.apiCancelOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse pagination(ListOrderCommand command) {
        if (null != command) {
            command.verifyPage();
            command.verifyPageSize(10);
            Pagination<Order> pagination = orderService.apiPagination(command);
            List<OrderRepresentation> data = mappingService.mapAsList(pagination.getData(), OrderRepresentation.class);

            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0,
                    new Pagination<OrderRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()),
                    ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse balancePay(BalancePayCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }

            OrderRepresentation order = mappingService.map(orderService.balancePay(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse offLinePay(OffLinePayCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }

            OrderRepresentation order = mappingService.map(orderService.offLinePay(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse exportExcel(CompanyOrderListCommand command) {
        List<OrderRepresentation> orderRepresentations =  mappingService.mapAsList(orderService.apiExportExcel(command), OrderRepresentation.class);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, orderRepresentations, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse driverCreateOrder(DriverCreateOrderCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            OrderRepresentation order = mappingService.map(orderService.driverCreateOrder(command), OrderRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }
}
