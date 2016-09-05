package pengyi.application.appversion.representation;

import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/4/13.
 */
public class AppVersionRepresentation {

    private String id;
    private Integer version;

    private String appVersion;  //app版本号
    private String updateDate;    //更新时间
    private String updateContent;   //更新内容
    private EnableStatus status;    //状态

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

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
