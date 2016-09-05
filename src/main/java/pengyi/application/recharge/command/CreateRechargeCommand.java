package pengyi.application.recharge.command;

import pengyi.core.type.PayType;
import pengyi.core.type.UserType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/4/11.
 */
public class CreateRechargeCommand {

    private UserType userType;
    private String userName;
    @NotNull(message = "{recharge.money,NotEmpty,message}")
    private BigDecimal money;
    private PayType payType;
    private String ip;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
