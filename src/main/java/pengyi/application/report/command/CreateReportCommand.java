package pengyi.application.report.command;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by liubowen on 2016/3/10.
 * 页面创建
 */
public class CreateReportCommand {

    @NotEmpty(message = "{report.reportUser,NotEmpty,message}")
    private String reportUser;                //举报人
    @NotEmpty(message = "{report.orderId,NotEmpty,message}")
    private String orderId;                        //举报订单
    @NotEmpty(message = "{report.description,NotEmpty,message}")
    private String description;                 //说明

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
