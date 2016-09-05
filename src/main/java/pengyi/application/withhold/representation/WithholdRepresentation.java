package pengyi.application.withhold.representation;

import pengyi.domain.model.base.Identity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现
 * Created by pengyi on 2016/5/6.
 */
public class WithholdRepresentation extends Identity {

    private String username;                        //提现人姓名
    private String userId;                          //提现人id
    private Date createTime;                        //提现时间
    private BigDecimal money;                       //提现金额
    private String detail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
