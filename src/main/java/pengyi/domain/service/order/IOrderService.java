package pengyi.domain.service.order;

import pengyi.application.order.command.*;
import pengyi.core.type.EvaluateStatus;
import pengyi.domain.model.order.Order;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IOrderService {

    Pagination<Order> pagination(ListOrderCommand command);

    Order show(String id);

    List<Order> searchByDriver(String driverId);

    Order updateEvaluate(String orderId, EvaluateStatus evaluateStatus);

    Order byOrderNumber(String orderNumber);

    void paySuccess(Order order);

    /********
     * api 方法
     ***********/
    Pagination<Order> apiCompanyOrderPagination(CompanyOrderListCommand command);

    Order apiCreateOrder(CreateOrderCommand command);

    Order apiReceiverOrder(ReceiveOrderCommand command);

    Order apiStartOrder(UpDateOrderStatusCommand command);

    Order apiWaitPayOrder(UpDateOrderStatusCommand command);

//    Order apiPayOrder(UpDateOrderStatusCommand command);

    Order apiCancelOrder(UpDateOrderStatusCommand command);

    Pagination<Order> apiPagination(ListOrderCommand command);

    Order balancePay(BalancePayCommand command);

    Order offLinePay(OffLinePayCommand command);

    boolean hasOrder(String userId, String orderId);

    boolean hasBalance(String userId);

    List<Order> exportExcel(ListOrderCommand command);

    List<Order> apiExportExcel(CompanyOrderListCommand command);

    Order driverCreateOrder(DriverCreateOrderCommand command);
}
