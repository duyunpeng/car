package pengyi.application.evaluate.command;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class EditEvaluateCommand {
    private String id;
    private Integer version;
    @NotEmpty(message = "{evaluate.evaluateUser.NotEmpty.message}")
    private String evaluateUser;                      //评价人
    @NotEmpty(message = "{evaluate.order.NotEmpty.message}")
    private String orderId;                             //订单

    @NotEmpty(message = "{evaluate.content.NotEmpty.message}")
    private String content;                          //评价内容

    @NotEmpty(message = "{evaluate.level.NotEmpty.message}")
    private Integer level;                              //评级

    public String getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(String evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
