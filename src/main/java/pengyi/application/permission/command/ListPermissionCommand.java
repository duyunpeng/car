package pengyi.application.permission.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/7.
 */
public class ListPermissionCommand extends BasicPaginationCommand {
    private String permissionName;
    private EnableStatus status;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
