package pengyi.domain.model.user;

import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;

import java.math.BigDecimal;
import java.util.Date;

/**
 * update by yjh
 * <p>
 * Created by pengyi on 2015/12/24.
 */
public class BaseUser extends Identity {

    private String name;
    private String userName;                           //手机号
    private String password;                        //密码
    private String salt;                            //密码盐
    private EnableStatus status;                         //是否启用(true=启用，false=禁用)
    private BigDecimal balance;                     //余额
    private Date createDate;                      //创建时间
    private Role userRole;                          //用户角色
    private String email;                           //邮箱
    private UserType userType;                               //1平台、2用户、3公司、4司机

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredentialsSalt() {
        return this.userName + this.salt;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public BaseUser() {
        super();
    }

    public BaseUser(String name) {
        this.name = name;
    }

    public BaseUser(String name, String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.balance = balance;
        this.createDate = createDate;
        this.userRole = userRole;
        this.email = email;
        this.userType = userType;
    }
}
