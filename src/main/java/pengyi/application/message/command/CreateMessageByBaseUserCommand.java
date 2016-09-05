package pengyi.application.message.command;

import pengyi.core.type.MessageType;
import pengyi.core.type.ShowType;

/**
 * Created by liubowen on 2016/3/17.
 */
public class CreateMessageByBaseUserCommand {
    private String sendBaseUser;                  //发送人
    private String receiveBaseUser;            //接收人
    private String content;                 //内容
    private MessageType type;                       //类型（0为系统消息）

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

}
