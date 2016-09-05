package pengyi.application.order.command;

/**
 * Created by YJH on 2016/3/22.
 */
public class ReceiveOrderCommand {

    private String orderId;

    private String receiveUser;

    private Integer version;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
