package pengyi.application.message.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.MessageType;
import pengyi.core.type.ShowType;

/**
 * Created by liubowen on 2016/3/8.
 */
public class MessageRepresentation {
    private String id;
    private Integer version;                        //数据库版本
    private BaseUserRepresentation sendBaseUser;                  //发送人
    private BaseUserRepresentation receiveBaseUser;               //接收人
    private String sendDate;                //发送时间
    private String receiveDate;             //接收时间
    private String content;                 //内容
    private MessageType type;                       //类型（0为系统消息）
    private ShowType showType;

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
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

    public BaseUserRepresentation getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(BaseUserRepresentation sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
    }

    public BaseUserRepresentation getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(BaseUserRepresentation receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
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

    public MessageRepresentation(String id, Integer version, BaseUserRepresentation sendBaseUser, BaseUserRepresentation receiveBaseUser, String sendDate, String receiveDate, String content, MessageType type, ShowType showType) {
        this.id = id;
        this.version = version;
        this.sendBaseUser = sendBaseUser;
        this.receiveBaseUser = receiveBaseUser;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.content = content;
        this.type = type;
        this.showType = showType;
    }

    public MessageRepresentation() {
    }
}
