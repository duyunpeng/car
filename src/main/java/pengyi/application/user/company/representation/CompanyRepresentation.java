package pengyi.application.user.company.representation;

import pengyi.application.area.representation.AreaRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
public class CompanyRepresentation extends BaseUserRepresentation {

    private String name;            //公司名
    private String folder;          //公司资质
    private String registerDate;    //注册时间
    private AreaRepresentation registerAddress;   //注册地点
    private AreaRepresentation operateAddress;    //运营地点
    private BigDecimal money;       //余额
    private Double level;           //等级

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public AreaRepresentation getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(AreaRepresentation registerAddress) {
        this.registerAddress = registerAddress;
    }

    public AreaRepresentation getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(AreaRepresentation operateAddress) {
        this.operateAddress = operateAddress;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }
}
