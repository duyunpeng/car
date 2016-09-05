package pengyi.application.moneydetailed.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.FlowType;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListMoneyDetailedCommand extends BasicPaginationCommand {

    private String userId;

    private String userName;

    private String date;

    private FlowType flowType;

    private String startTime;

    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }
}
