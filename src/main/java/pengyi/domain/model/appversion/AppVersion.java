package pengyi.domain.model.appversion;

import pengyi.core.type.EnableStatus;
import pengyi.domain.model.base.Identity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/13.
 */
public class AppVersion extends Identity {

    private String appVersion;  //app版本号
    private Date updateDate;    //更新时间
    private String updateContent;   //更新内容
    private EnableStatus status;    //状态

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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

    public AppVersion() {
        super();
    }

    public AppVersion(String appVersion, Date updateDate, String updateContent, EnableStatus status) {
        this.appVersion = appVersion;
        this.updateDate = updateDate;
        this.updateContent = updateContent;
        this.status = status;
    }
}
