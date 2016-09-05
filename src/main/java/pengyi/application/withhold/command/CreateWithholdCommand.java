package pengyi.application.withhold.command;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2016/5/6.
 */
public class CreateWithholdCommand {

    private String loginUser;
    private String userId;
    private BigDecimal money;
    private String detail;

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
