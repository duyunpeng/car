package pengyi.application.urlresources.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditUrlResourcesCommand {

    private String id;
    private Integer version;

    @NotEmpty(message = "{url_resource.urlName.NotEmpty.message}")
    private String urlName;                     //路径名
    @NotEmpty(message = "{url_resource.urlPermission.NotEmpty.message}")
    private String description;                 //描述
    private String[] urlPermission;     //路径权限列表

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
}
