package pengyi.domain.model.withdraw;

import pengyi.core.type.WithdrawStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现
 * Created by pengyi on 2016/5/6.
 */
public class Withdraw extends Identity {

    private BaseUser baseUser;                      //提现人
    private Date createTime;                        //提现时间
    private BigDecimal money;                       //提现金额
    private WithdrawStatus status;                  //提现状态
    private Date finishTime;                        //完成时间

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

    public WithdrawStatus getStatus() {
        return status;
    }

    public void setStatus(WithdrawStatus status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Withdraw() {
        super();
    }

    public Withdraw(BaseUser baseUser, Date createTime, BigDecimal money, WithdrawStatus status, Date finishTime) {
        super();
        this.baseUser = baseUser;
        this.createTime = createTime;
        this.money = money;
        this.status = status;
        this.finishTime = finishTime;
    }

}
