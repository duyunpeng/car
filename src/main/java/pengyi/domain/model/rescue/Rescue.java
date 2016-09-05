package pengyi.domain.model.rescue;

import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;

import java.util.Date;

/**
 * 救援
 * Created by pengyi on 2016/3/4.
 */
public class Rescue extends Identity {

    private BaseUser applyUser;                 //申请人
    private Date applyTime;                     //申请时间
    private RescueType rescueType;              //救援类型
    private String description;                 //救援说明
    private Driver driver;                      //救援司机
    private Date rescueTime;                    //救援时间
    private RescueStatus status;                //救援状态（1待救援、2救援中、3已救援）
    private Date finishTime;                    //救援完成时间
    private String images;                      //救援图片
    private String rescueAddress;               //救援地址
    private Area area;                          //救援地址

    private String name;
    private String phone;

    public BaseUser getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(BaseUser applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public RescueType getRescueType() {
        return rescueType;
    }

    public void setRescueType(RescueType rescueType) {
        this.rescueType = rescueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(Date rescueTime) {
        this.rescueTime = rescueTime;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Rescue() {
        super();
    }

    public Rescue(BaseUser applyUser, Date applyTime, RescueType rescueType, String description, Driver driver, Date rescueTime, RescueStatus status, Date finishTime, String images, String rescueAddress,String name,String phone, Area area) {
        this.applyUser = applyUser;
        this.applyTime = applyTime;
        this.rescueType = rescueType;
        this.description = description;
        this.driver = driver;
        this.rescueTime = rescueTime;
        this.status = status;
        this.finishTime = finishTime;
        this.images = images;
        this.rescueAddress = rescueAddress;
        this.name = name;
        this.phone = phone;
        this.area = area;
    }
}
