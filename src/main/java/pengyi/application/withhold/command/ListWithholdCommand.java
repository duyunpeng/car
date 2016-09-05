package pengyi.application.withhold.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.WithdrawStatus;

/**
 * Created by pengyi on 2016/5/6.
 */
public class ListWithholdCommand extends BasicPaginationCommand {

    private String loginUser;
    private String baseUser;                          //被扣款人
    private String startTime;                         //扣款时间
    private String endTime;

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

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

}
