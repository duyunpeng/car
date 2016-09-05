package pengyi.application.rescue.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListRescueCommand extends BasicPaginationCommand {
    private String applyUser;                   //申请人
    private String driver;                      //救援司机
    private RescueStatus status;                //救援状态（1待救援、2救援中、3已救援）
    private RescueType rescueType;              //救援类型
    private String area;                        //区域
    private String company;

    public RescueType getRescueType() {
        return rescueType;
    }

    public void setRescueType(RescueType rescueType) {
        this.rescueType = rescueType;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
