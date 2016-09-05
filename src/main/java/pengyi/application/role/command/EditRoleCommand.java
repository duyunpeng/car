package pengyi.application.role.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditRoleCommand {

    private String id;
    private Integer version;

    @NotEmpty(message = "{role.roleName.NotEmpty.message}")
    private String roleName;                        //角色名
    @NotEmpty(message = "{role.description.NotEmpty.message}")
    private String description;                     //描述
    private String[] permissions;            //权限列表

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

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
