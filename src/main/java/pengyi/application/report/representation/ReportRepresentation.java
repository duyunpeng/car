package pengyi.application.report.representation;

import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.type.ReportStatus;

import java.util.Date;

/**
 * Created by liubowen on 2016/3/10.
 *
 */
public class ReportRepresentation {
    private Integer version;
    private String id;
    private UserRepresentation reportUser;      //举报人
    private OrderRepresentation order;          //举报订单
    private String reportTime;                  //举报时间
    private String  startDealTime;               //开始处理时间
    private String endDealTime;                 //处理完成时间
    private String description;                 //说明
    private ReportStatus status;                //状态（待处理、处理中、处理完成）
    private String handleResult;                //处理结果

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserRepresentation getReportUser() {
        return reportUser;
    }

    public void setReportUser(UserRepresentation reportUser) {
        this.reportUser = reportUser;
    }

    public OrderRepresentation getOrder() {
        return order;
    }

    public void setOrder(OrderRepresentation order) {
        this.order = order;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(String startDealTime) {
        this.startDealTime = startDealTime;
    }

    public String getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(String endDealTime) {
        this.endDealTime = endDealTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }
}
