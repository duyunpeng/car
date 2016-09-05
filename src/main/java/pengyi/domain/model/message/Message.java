package pengyi.domain.model.message;

import pengyi.core.type.MessageType;
import pengyi.core.type.ShowType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

import java.util.Date;

/**
 * 站内信
 * update by liubowen 2016/3/16
 * Created by pengyi on 2016/3/4.
 */
public class Message extends Identity {

    private BaseUser sendBaseUser;                  //发送人
    private BaseUser receiveBaseUser;               //接收人
    private Date sendDate;                //发送时间
    private Date receiveDate;             //接收时间
    private String content;                 //内容
    private MessageType type;                       //类型（0为系统消息）
    private ShowType showType;                      //是否显示（0为显示，1为不显示）

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public BaseUser getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(BaseUser sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
    }

    public BaseUser getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(BaseUser receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Message() {
        super();
    }

    public Message(BaseUser sendBaseUser, BaseUser receiveBaseUser, Date sendDate, Date receiveDate, String content, MessageType type, ShowType showType) {
        super();
        this.sendBaseUser = sendBaseUser;
        this.receiveBaseUser = receiveBaseUser;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.content = content;
        this.type = type;
        this.showType = showType;
    }
}
