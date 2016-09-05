package pengyi.domain.model.urlresources;

import pengyi.core.type.EnableStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.permission.Permission;

import java.util.List;

/**
 *
 * update by yjh
 *
 * 请求路径
 * Created by pengyi on 2016/3/4.
 */
public class UrlResources extends Identity{

    private String urlName;                     //路径名
    private String description;                 //描述
    private List<Permission> urlPermission;     //路径权限列表
    private EnableStatus status;                     //是否启用（true=启用，false=禁用）

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

    public List<Permission> getUrlPermission() {
        return urlPermission;
    }

    public void setUrlPermission(List<Permission> urlPermission) {
        this.urlPermission = urlPermission;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public UrlResources() {
        super();
    }

    public UrlResources(String urlName, String description, List<Permission> urlPermission, EnableStatus status) {
        this.urlName = urlName;
        this.description = description;
        this.urlPermission = urlPermission;
        this.status = status;
    }
}
