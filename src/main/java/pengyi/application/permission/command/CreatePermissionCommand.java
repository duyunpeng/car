package pengyi.application.permission.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreatePermissionCommand {

    @NotEmpty(message = "{permission.permissionName,NotEmpty,message}")
    private String permissionName;                  //权限名
    @NotEmpty(message = "{permission.description,NotEmpty,message}")
    private String description;                     //描述
    @NotNull(message = "{permission.status.NotNull,message}")
    private EnableStatus status;                         //是否启用

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
