package pengyi.application.permission.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditPermissionCommand {

    private String id;
    private Integer version;

    @NotEmpty(message = "{permission.permissionName,NotEmpty,message}")
    private String permissionName;                  //权限名
    @NotEmpty(message = "{permission.description,NotEmpty,message}")
    private String description;                     //描述

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

}
