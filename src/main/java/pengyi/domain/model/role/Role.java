package pengyi.domain.model.role;

import pengyi.core.type.EnableStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.permission.Permission;

import java.util.List;

/**
 * 角色
 * Created by pengyi on 2016/3/4.
 */
public class Role extends Identity{

    private String roleName;                        //角色名
    private String description;                     //描述
    private EnableStatus status;                         //是否启用
    private List<Permission> permissions;            //权限列表

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role() {
        super();
    }

    public Role(String roleName, String description, EnableStatus status, List<Permission> permissions) {
        this.roleName = roleName;
        this.description = description;
        this.status = status;
        this.permissions = permissions;
    }
}
