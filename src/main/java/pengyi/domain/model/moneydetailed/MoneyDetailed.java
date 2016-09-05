package pengyi.domain.model.moneydetailed;

import pengyi.core.type.FlowType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/9.
 */
public class MoneyDetailed extends Identity {

    private BaseUser baseUser;      //用户
    private FlowType flowType;  //资金流向类型
    private BigDecimal money;   //金额
    private String description;     //说明（如：专车订单+订单号）
    private BigDecimal oldMoney;    //原有金额
    private BigDecimal newMoney;    //现有金额
    private Date createDate;        //创建日期

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(BigDecimal oldMoney) {
        this.oldMoney = oldMoney;
    }

    public BigDecimal getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(BigDecimal newMoney) {
        this.newMoney = newMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public MoneyDetailed() {
        super();
    }

    public MoneyDetailed(BaseUser baseUser, FlowType flowType, BigDecimal money, String description, BigDecimal oldMoney, BigDecimal newMoney, Date createDate) {
        this.baseUser = baseUser;
        this.flowType = flowType;
        this.money = money;
        this.description = description;
        this.oldMoney = oldMoney;
        this.newMoney = newMoney;
        this.createDate = createDate;
    }
}
