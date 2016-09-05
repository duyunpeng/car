package pengyi.application.role.representation;

import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.core.type.EnableStatus;
import pengyi.domain.model.permission.Permission;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public class RoleRepresentation {

    private String id;
    private Integer version;
    private String roleName;                        //角色名
    private String description;                     //描述
    private EnableStatus status;                         //是否启用
    private List<PermissionRepresentation> permissions;            //权限列表

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

    public List<PermissionRepresentation> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionRepresentation> permissions) {
        this.permissions = permissions;
    }
}
