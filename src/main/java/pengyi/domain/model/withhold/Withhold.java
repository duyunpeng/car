package pengyi.domain.model.withhold;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现
 * Created by pengyi on 2016/5/6.
 */
public class Withhold extends Identity {

    private BaseUser baseUser;                      //扣款人
    private Date createTime;                        //扣款时间
    private BigDecimal money;                       //扣款金额
    private String detail;                          //扣款说明

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Withhold() {
        super();
    }

    public Withhold(BaseUser baseUser, Date createTime, BigDecimal money, String detail) {
        super();
        this.baseUser = baseUser;
        this.createTime = createTime;
        this.money = money;
        this.detail = detail;
    }
}
