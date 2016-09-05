package pengyi.application.user.company.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;
import pengyi.domain.model.area.Area;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditCompanyCommand extends BaseEditBaseUserCommand {

    private String name;            //公司名
    private String registerAddress;   //注册地点
    private String operateAddress;    //运营地点

    private String folder;      //公司纸质照片

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
