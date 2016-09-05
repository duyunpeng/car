package pengyi.application.user.user.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.Sex;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class UserRepresentation extends BaseUserRepresentation {

    private String name;                    //用户名
    private String head;                    //头像
    private Sex sex;                        //性别（0为男，2为女）
    private Integer integral;                   //积分
    private BigDecimal money;               //余额
    private Integer reportCount;                //举报次数

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }
}
