package pengyi.application.rescue.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/3/9.
 */
public class EditRescueCommand {
    private String id;
    private Integer version;

    @NotEmpty(message = "{rescue.applyUser.NotEmpty.message}")
    private String applyUser;                 //申请人

    @NotNull(message = "{rescue.type.Notnull.message}")
    private RescueType type;                           //救援类型

    @NotEmpty(message = "{rescue.description.NotEmpty.message}")
    private String description;                 //救援说明

    @NotEmpty(message = "{rescue.driver.NotEmpty.message}")
    private String driver;                      //救援司机

    @NotEmpty(message = "{rescue.rescueStatus.Notnull.message}")
    private RescueStatus rescueStatus;                         //救援状态（1待救援、2救援中、3已救援）

    public RescueStatus getRescueStatus() {
        return rescueStatus;
    }

    public void setRescueStatus(RescueStatus rescueStatus) {
        this.rescueStatus = rescueStatus;
    }

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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public RescueType getType() {
        return type;
    }

    public void setType(RescueType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }


}
