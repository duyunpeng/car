package pengyi.application.urlresources.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreateUrlResourcesCommand {

    @NotEmpty(message = "{url_resource.urlName.NotEmpty.message}")
    private String urlName;                     //路径名
    @NotEmpty(message = "{url_resource.description.NotEmpty.message}")
    private String description;                 //描述
    private String[] urlPermission;     //路径权限列表
    @NotNull(message = "{url_resource.status.NotNull.message}")
    private EnableStatus status;                     //是否启用

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getUrlPermission() {
        return urlPermission;
    }

    public void setUrlPermission(String[] urlPermission) {
        this.urlPermission = urlPermission;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
