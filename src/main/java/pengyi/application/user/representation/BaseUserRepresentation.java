package pengyi.application.user.representation;

import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseUserRepresentation {

    private String id;
    private Integer version;

    private String userName;                           //用户名
    private String password;                        //密码
    private String salt;                            //密码盐
    private EnableStatus status;                         //是否启用
    private BigDecimal balance;                     //余额
    private String createDate;                      //创建时间
    private RoleRepresentation userRole;                          //用户角色
    private String email;                           //邮箱
    private UserType userType;                               //1平台、2用户、3公司、4司机

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public RoleRepresentation getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleRepresentation userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
