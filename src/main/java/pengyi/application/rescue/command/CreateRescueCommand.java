package pengyi.application.rescue.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;

import javax.validation.constraints.NotNull;

/**
 * Created by LvDi on 2016/3/9.
 */
public class CreateRescueCommand {

    @NotEmpty(message = "{rescue.applyUser.NotEmpty.message}")
    private String applyUser;                   //申请人

    private String userName;

    private String verificationCode;

    @NotNull(message = "{rescue.type.Notnull.message}")
    private RescueType rescueType;                           //救援类型

    @NotEmpty(message = "{rescue.description.NotEmpty.message}")
    private String description;                 //救援说明

    private String rescueAddress;               //救援地址

    private String area;

    private String name;
    private String phone;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRescueAddress() {
        return rescueAddress;
    }

    public void setRescueAddress(String rescueAddress) {
        this.rescueAddress = rescueAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
}
