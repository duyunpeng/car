package pengyi.application.withdraw.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.WithdrawStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yjh on 16-6-6.
 */
public class WithdrawExtendRepresentation {

    private String id;
    private Integer version;

    private BaseUserRepresentation user;
    private Date createTime;                        //提现时间
    private BigDecimal money;                       //提现金额
    private WithdrawStatus status;                  //提现状态
    private Date finishTime;                        //完成时间

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

    public BaseUserRepresentation getUser() {
        return user;
    }

    public void setUser(BaseUserRepresentation user) {
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
}
