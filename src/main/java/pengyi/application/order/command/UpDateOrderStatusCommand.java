package pengyi.application.order.command;

import pengyi.core.type.OrderStatus;

/**
 * Created by YJH on 2016/3/8.
 */
public class UpDateOrderStatusCommand {

    private String orderId;
    private Integer version;

    private OrderStatus status;

    private Integer km;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }
}
