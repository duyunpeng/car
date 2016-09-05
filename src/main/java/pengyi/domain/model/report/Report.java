package pengyi.domain.model.report;

import pengyi.core.type.ReportStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;

import java.util.Date;

/**
 * 举报
 * Created by pengyi on 2016/3/4.
 * update by liubowen on 2016/3/7 添加开始处理时间，处理完成时间，举报时间
 * update by liubowen on 2016/3/16 添加处理结果。
 */
public class Report extends Identity {

    private BaseUser reportUser;                //举报人
    private Order order;                        //举报订单
    private Date reportTime;                  //举报时间
    private Date startDealTime;               //开始处理时间
    private Date endDealTime;                 //处理完成时间
    private String description;                 //说明
    private ReportStatus status;                  //状态（待处理、处理中、处理完成）
    private String handleResult;                   //处理结果

    public BaseUser getReportUser() {
        return reportUser;
    }

    public void setReportUser(BaseUser reportUser) {
        this.reportUser = reportUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(Date startDealTime) {
        this.startDealTime = startDealTime;
    }

    public Date getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(Date endDealTime) {
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

    public Report() {
        super();
    }

    public Report(BaseUser reportUser, Order order, Date reportTime, Date startDealTime, Date endDealTime, String description, ReportStatus status,String handleResult) {
        this.reportUser = reportUser;
        this.order = order;
        this.reportTime = reportTime;
        this.startDealTime = startDealTime;
        this.endDealTime = endDealTime;
        this.description = description;
        this.status = status;
        this.handleResult=handleResult;
    }
}
