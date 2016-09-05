package pengyi.application.rescue.representation;

import pengyi.application.area.representation.AreaRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;

/**
 * Created by LvDi on 2016/3/9.
 */
public class RescueRepresentation {
    private String id;
    private Integer version;

    private BaseUserRepresentation applyUser;   //申请人
    private String applyTime;                   //申请时间
    private RescueType rescueType;              //救援类型
    private String description;                 //救援说明
    private DriverRepresentation driver;        //救援司机
    private String rescueTime;                  //救援时间
    private RescueStatus status;                //救援状态（1待救援、2救援中、3已救援）
    private String finishTime;                  //救援完成时间
    private String images;                      //救援图片
    private String rescueAddress;               //救援地址

    private AreaRepresentation area;            //救援地址

    private String name;
    private String phone;

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

    public BaseUserRepresentation getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(BaseUserRepresentation applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public RescueType getRescueType() {
        return rescueType;
    }

    public void setRescueType(RescueType rescueType) {
        this.rescueType = rescueType;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DriverRepresentation getDriver() {
        return driver;
    }

    public void setDriver(DriverRepresentation driver) {
        this.driver = driver;
    }

    public String getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(String rescueTime) {
        this.rescueTime = rescueTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getRescueAddress() {
        return rescueAddress;
    }

    public void setRescueAddress(String rescueAddress) {
        this.rescueAddress = rescueAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AreaRepresentation getArea() {
        return area;
    }

    public void setArea(AreaRepresentation area) {
        this.area = area;
    }
}
