package pengyi.application.message.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ShowType;

import java.util.Date;

/**
 * Created by liubowen on 2016/3/8.
 */
public class ListMessageCommand extends BasicPaginationCommand {
    private String sendBaseUser;
    private String receiveBaseUser;
    private String company;
    private String beginTime;
    private String endTime;
    private String content;                 //内容
    private ShowType showType;              //是否显示

    public String getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(String sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
    }

    public String getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(String receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }
}
