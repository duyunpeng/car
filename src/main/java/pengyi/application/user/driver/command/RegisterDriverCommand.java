package pengyi.application.user.driver.command;

import pengyi.core.type.DriverType;

import java.util.Date;

/**
 * Created by YJH on 2016/3/21.
 */
public class RegisterDriverCommand {

    private String userName;
    private String password;
    private String verificationCode;
    private String identityCardPic;
    private String drivingLicencePic;
    private String company;
    private DriverType driverType;
    private String startDriveDate;

    private String name;                    //姓名
    private String travelPic;               //行驶证
    private String drivingLicenceType;      //驾驶证类型（C1,C2,B1,B2,A1,A2）
    private String phone;               //电话
    private String businessPic;         //营业资格证
    private String workPic;             //从业资格证

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getIdentityCardPic() {
        return identityCardPic;
    }

    public void setIdentityCardPic(String identityCardPic) {
        this.identityCardPic = identityCardPic;
    }

    public String getDrivingLicencePic() {
        return drivingLicencePic;
    }

    public void setDrivingLicencePic(String drivingLicencePic) {
        this.drivingLicencePic = drivingLicencePic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getStartDriveDate() {
        return startDriveDate;
    }

    public void setStartDriveDate(String startDriveDate) {
        this.startDriveDate = startDriveDate;
    }

    public String getTravelPic() {
        return travelPic;
    }

    public void setTravelPic(String travelPic) {
        this.travelPic = travelPic;
    }

    public String getDrivingLicenceType() {
        return drivingLicenceType;
    }

    public void setDrivingLicenceType(String drivingLicenceType) {
        this.drivingLicenceType = drivingLicenceType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    public String getWorkPic() {
        return workPic;
    }

    public void setWorkPic(String workPic) {
        this.workPic = workPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
