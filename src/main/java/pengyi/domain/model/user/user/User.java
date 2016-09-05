package pengyi.domain.model.user.user;

import pengyi.core.type.EnableStatus;
import pengyi.core.type.Sex;
import pengyi.core.type.UserType;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * update by yjh
 * <p>
 * 用户
 * Created by pengyi on 2016/3/4.
 */
public class User extends BaseUser {

    private String head;                    //头像
    private Sex sex;                        //性别（0为男，2为女）
    private Integer integral;                   //积分
    private Integer reportCount;                //举报次数

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public User() {
        super();
    }

    public User(String name, String head, Sex sex, Integer integral, Integer reportCount) {
        super(name);
        this.head = head;
        this.sex = sex;
        this.integral = integral;
        this.reportCount = reportCount;
    }

    public User(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType, String name, String head, Sex sex, Integer integral, Integer reportCount) {
        super(name, userName, password, salt, status, balance, createDate, userRole, email, userType);
        this.head = head;
        this.sex = sex;
        this.integral = integral;
        this.reportCount = reportCount;
    }

    public User(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType) {
        super("", userName, password, salt, status, balance, createDate, userRole, email, userType);
    }
}
