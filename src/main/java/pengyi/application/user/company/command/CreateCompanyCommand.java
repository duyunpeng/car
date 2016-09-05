package pengyi.application.user.company.command;

import pengyi.application.user.command.BaseCreateBaseUserCommand;

/**
 * Created by YJH on 2016/3/16.
 */
public class CreateCompanyCommand extends BaseCreateBaseUserCommand {

    private String name;            //公司名
    private String folder;          //公司资质
    private String registerDate;    //公司注册时间
    private String registerAddress;   //注册地点
    private String operateAddress;    //运营地点

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

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
