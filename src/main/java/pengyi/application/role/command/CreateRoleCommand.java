package pengyi.application.role.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreateRoleCommand {

    @NotEmpty(message = "{role.roleName.NotEmpty.message}")
    private String roleName;                        //角色名
    @NotEmpty(message = "{role.description.NotEmpty.message}")
    private String description;                     //描述
    @NotNull(message = "{role.status.NotNull.message}")
    private EnableStatus status;                         //是否启用
    private String[] permissions;                   //权限列表

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
