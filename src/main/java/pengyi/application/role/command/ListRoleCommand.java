package pengyi.application.role.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/7.
 */
public class ListRoleCommand extends BasicPaginationCommand {

    private String roleName;
    private EnableStatus status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
