package pengyi.application.appversion.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/4/13.
 */
public class ListAppVersionCommand extends BasicPaginationCommand {

    private String appVersion;
    private String startUpdateDate;
    private String endUpDateDate;
    private EnableStatus status;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getStartUpdateDate() {
        return startUpdateDate;
    }

    public void setStartUpdateDate(String startUpdateDate) {
        this.startUpdateDate = startUpdateDate;
    }

    public String getEndUpDateDate() {
        return endUpDateDate;
    }

    public void setEndUpDateDate(String endUpDateDate) {
        this.endUpDateDate = endUpDateDate;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
