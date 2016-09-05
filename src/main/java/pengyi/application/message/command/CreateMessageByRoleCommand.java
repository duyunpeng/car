package pengyi.application.message.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.MessageType;
import pengyi.core.type.ShowType;
import pengyi.domain.model.user.BaseUser;

/**
 * Created by liubowen on 2016/3/8.
 */
public class CreateMessageByRoleCommand {
    private String sendBaseUser;                  //发送人
    @NotEmpty(message = "{message.content.create.message}")
    private String content;                 //内容
    private MessageType type;                       //类型（0为系统消息）
    @NotEmpty(message = "{message.create.Role.message}")
    private String userRole;                       //用户角色

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(String sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
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
