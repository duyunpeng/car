package pengyi.domain.model.recharge;

import pengyi.core.type.PayType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi on 2016/4/11.
 */
public class Recharge extends Identity {

    private BaseUser user;              //充值人
    private Date createTime;            //创建时间
    private BigDecimal money;           //充值金额
    private Date payTime;               //支付时间
    private PayType payType;            //支付方式
    private String payNo;               //支付号
    private boolean payed;             //是否支付

    public BaseUser getUser() {
        return user;
    }

    public void setUser(BaseUser user) {
        this.user = user;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Recharge() {
    }

    public Recharge(BaseUser user, BigDecimal money, PayType payType) {
        this.user = user;
        this.createTime = new Date();
        this.money = money;
        this.payType = payType;
        this.payed = false;
    }
}
