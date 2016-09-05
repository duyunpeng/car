package pengyi.application.user.driver.representation;

import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.AuthStatus;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
public class DriverRepresentation extends BaseUserRepresentation {

    private String name;                    //姓名
    private String head;                    //头像
    private CompanyRepresentation company;                //公司
    private Sex sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //等级
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private Integer reportCount;                //举报次数
    private Boolean online;                 //是否在线
    private DriverType driverType;                       //类型（1代驾、2专车、3出租车）
    private String identityCardPic;                   //身份证照片
    private String drivingLicencePic;          //驾驶证照片
    private AuthStatus authStatus;      //审核状态

    private String travelPic;               //行驶证
    private String drivingLicenceType;      //驾驶证类型（C1,C2,B1,B2,A1,A2）
    private String phone;               //电话
    private String businessPic;         //营业资格证
    private String workPic;             //从业资格证
    private String bankCardNo;              //银行卡号
    private String bankName;            //银行名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public CompanyRepresentation getCompany() {
        return company;
    }

    public void setCompany(CompanyRepresentation company) {
        this.company = company;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
}
