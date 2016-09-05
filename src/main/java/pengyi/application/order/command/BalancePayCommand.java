package pengyi.application.order.command;

/**
 * Created by YJH on 2016/4/6.
 */
public class BalancePayCommand {
    private String orderId;

    private Integer version;

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
}
