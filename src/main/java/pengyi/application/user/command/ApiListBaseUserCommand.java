package pengyi.application.user.command;

import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/15.
 */
public class ApiListBaseUserCommand {

    private String id;
    private String userName;
    private EnableStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

}
