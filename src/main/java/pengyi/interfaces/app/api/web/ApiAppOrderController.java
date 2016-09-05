package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IApiOrderAppService;
import pengyi.application.order.command.*;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.core.exception.OrderIsStartException;
import pengyi.core.type.UserType;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/22.
 */
@Controller
@RequestMapping("/api/app/order")
public class ApiAppOrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiOrderAppService apiOrderAppService;

    @RequestMapping(value = "/wait_order")
    @ResponseBody
    public BaseResponse waitOrder(ListOrderCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderAppService.waitOrderPagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/pagination")
    @ResponseBody
    public BaseResponse pagination(ListOrderCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        if (baseUser.getUserType() == UserType.DRIVER) {
            command.setReceiveUser(baseUser.getId());
        } else {
            command.setOrderUser(baseUser.getId());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.pagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public BaseResponse show(String orderId, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }

        BaseResponse response = null;
        try {
            response = apiOrderAppService.show(orderId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/create_order")
    @ResponseBody
    public BaseResponse createOrder(CreateOrderCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setOrderUser(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiOrderAppService.createOrder(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/driver_create_order")
    @ResponseBody
    public BaseResponse driverCreateOrder(DriverCreateOrderCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setDriverId(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiOrderAppService.driverCreateOrder(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/receive_order")
    @ResponseBody
    public BaseResponse receiveOrder(ReceiveOrderCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setReceiveUser(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiOrderAppService.receiveOrder(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/start_order")
    @ResponseBody
    public BaseResponse startOrder(UpDateOrderStatusCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.startOrder(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/wait_pay_order")
    @ResponseBody
    public BaseResponse waitPayOrder(UpDateOrderStatusCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.waitPayOrder(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

//    @RequestMapping(value = "/pay_order")
//    @ResponseBody
//    public BaseResponse payOrder(UpDateOrderStatusCommand command, HttpSession session) {
//        long startTime = System.currentTimeMillis();
//        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
//        if (null == baseUser) {
//            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
//                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
//        }
//        BaseResponse response = null;
//        try {
//            response = apiOrderAppService.payOrder(command);
//        } catch (ConcurrencyException e) {
//            logger.warn(e.getMessage());
//            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
//        } catch (Exception e) {
//            logger.warn(e.getMessage());
//            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
//        }
//        response.setDebug_time(System.currentTimeMillis() - startTime);
//        return response;
//    }

    @RequestMapping(value = "/cancel_order")
    @ResponseBody
    public BaseResponse cancelOrder(UpDateOrderStatusCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.cancelOrder(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (OrderIsStartException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/balance_pay")
    @ResponseBody
    public BaseResponse balancePay(BalancePayCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.balancePay(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (NotSufficientFundsException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS, 0, null, ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/off_line_pay")
    @ResponseBody
    public BaseResponse offLinePay(OffLinePayCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        BaseResponse response = null;
        try {
            response = apiOrderAppService.offLinePay(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ORDER_UPDATED, 0, null, ResponseCode.RESPONSE_CODE_ORDER_UPDATED.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
