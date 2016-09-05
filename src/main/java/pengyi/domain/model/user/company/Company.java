package pengyi.domain.model.user.company;

import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * update by yjh
 *
 * 公司
 * Created by pengyi on 2016/3/4.
 */
public class Company extends BaseUser {

    private String folder;          //公司资质
    private Date registerDate;    //注册时间
    private Area registerAddress;   //注册地点
    private Area operateAddress;    //运营地点
    private Double level;           //等级

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Area getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Area registerAddress) {
        this.registerAddress = registerAddress;
    }

    public Area getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(Area operateAddress) {
        this.operateAddress = operateAddress;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Company() {
        super();
    }

    public Company(String name, String folder, Date registerDate, Area registerAddress, Area operateAddress, Double level) {
        super(name);
        this.folder = folder;
        this.registerDate = registerDate;
        this.registerAddress = registerAddress;
        this.operateAddress = operateAddress;
        this.level = level;
    }

    public Company(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType, String name, String folder, Date registerDate, Area registerAddress, Area operateAddress, Double level) {
        super(name, userName, password, salt, status, balance, createDate, userRole, email, userType);
        this.folder = folder;
        this.registerDate = registerDate;
        this.registerAddress = registerAddress;
        this.operateAddress = operateAddress;
        this.level = level;
    }

    public Company(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType){
        super("", userName, password, salt, status, balance, createDate, userRole, email, userType);
    }
}
