package pengyi.application.user.command;

import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.domain.model.role.Role;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseCreateBaseUserCommand {

    private String userName;                           //用户名
    private String password;                        //密码
    private String confirmPassword;                 //确认密码
    private EnableStatus status;                      //是否启用(true=启用，false=禁用)
    private String userRole;                        //用户角色
    private String email;                           //邮箱
    private UserType userType;                               //1平台、2用户、3公司、4司机

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


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
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
