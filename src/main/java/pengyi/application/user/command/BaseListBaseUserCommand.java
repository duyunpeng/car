package pengyi.application.user.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseListBaseUserCommand extends BasicPaginationCommand {

    private String userName;
    private EnableStatus status;
    private UserType userType;
    private String roleName;

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
