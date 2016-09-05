package pengyi.domain.model.user.driver;

import pengyi.core.type.*;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;

import java.math.BigDecimal;
import java.util.Date;


/**
 * update by yjh
 * <p/>
 * 司机
 * Created by pengyi on 2016/3/4.
 */
public class Driver extends BaseUser {

    private String head;                    //头像
    private Company company;                //公司
    private Sex sex;                        //性别（0为男，1为女）
    private Double level;                   //评级（12345）
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private Integer reportCount;            //举报次数
    private Boolean online;                 //是否在线
    private DriverType driverType;          //类型
    private String identityCardPic;         //身份证照片
    private String drivingLicencePic;       //驾驶证照片
    private Date startDriveDate;         //开始驾驶时间
    private AuthStatus authStatus;      //审核状态

    private String travelPic;               //行驶证
    private String drivingLicenceType;      //驾驶证类型（C1,C2,B1,B2,A1,A2）
    private String phone;               //电话
    private String businessPic;         //营业资格证
    private String workPic;             //从业资格证
    private String bankCardNo;              //银行卡号
    private String bankName;            //银行名称

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
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

    public Date getStartDriveDate() {
        return startDriveDate;
    }

    public void setStartDriveDate(Date startDriveDate) {
        this.startDriveDate = startDriveDate;
    }

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
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

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Driver() {
        super();
    }

    public Driver(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType, String name, String head, Company company, Sex sex, Double level, Double longitude, Double latitude, Integer reportCount, Boolean online, DriverType driverType, String identityCardPic, String drivingLicencePic, Date startDriveDate, AuthStatus authStatus,
                  String travelPic, String drivingLicenceType, String phone, String businessPic, String workPic, String bankCardNo, String bankName) {
        super(name, userName, password, salt, status, balance, createDate, userRole, email, userType);
        this.head = head;
        this.company = company;
        this.sex = sex;
        this.level = level;
        this.longitude = longitude;
        this.latitude = latitude;
        this.reportCount = reportCount;
        this.online = online;
        this.driverType = driverType;
        this.identityCardPic = identityCardPic;
        this.drivingLicencePic = drivingLicencePic;
        this.startDriveDate = startDriveDate;
        this.authStatus = authStatus;
        this.travelPic = travelPic;
        this.drivingLicenceType = drivingLicenceType;
        this.phone = phone;
        this.businessPic = businessPic;
        this.workPic = workPic;
        this.bankCardNo = bankCardNo;
        this.bankName = bankName;
    }

    public Driver(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType) {
        super("", userName, password, salt, status, balance, createDate, userRole, email, userType);
    }
}
