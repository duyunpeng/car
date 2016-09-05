package pengyi.application.withdraw.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.WithdrawStatus;

import java.util.Date;

/**
 * Created by pengyi on 2016/5/6.
 */
public class ListWithdrawCommand extends BasicPaginationCommand {

    private String baseUser;                        //提现人
    private String startTime;                         //提现时间
    private String endTime;
    private WithdrawStatus status;                  //提现状态


    public String getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(String baseUser) {
        this.baseUser = baseUser;
    }

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

    public WithdrawStatus getStatus() {
        return status;
    }

    public void setStatus(WithdrawStatus status) {
        this.status = status;
    }
}
