package pengyi.application.order;

import pengyi.application.order.command.*;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiOrderAppService {

    BaseResponse waitOrderPagination(ListOrderCommand command);

    BaseResponse companyOrderPagination(CompanyOrderListCommand command);

    BaseResponse createOrder(CreateOrderCommand command);

    BaseResponse receiveOrder(ReceiveOrderCommand command);

    BaseResponse startOrder(UpDateOrderStatusCommand command);

    BaseResponse show(String orderId);

    BaseResponse waitPayOrder(UpDateOrderStatusCommand command);

//    BaseResponse payOrder(UpDateOrderStatusCommand command);

    BaseResponse cancelOrder(UpDateOrderStatusCommand command);

    BaseResponse pagination(ListOrderCommand command);


    BaseResponse balancePay(BalancePayCommand command);

    BaseResponse offLinePay(OffLinePayCommand command);

    BaseResponse exportExcel(CompanyOrderListCommand command);

    BaseResponse driverCreateOrder(DriverCreateOrderCommand command);
}
