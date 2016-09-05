package pengyi.application.user.company.command;

import pengyi.application.user.command.BaseListBaseUserCommand;

/**
 * Created by YJH on 2016/3/11.
 */
public class BaseListCompanyCommand extends BaseListBaseUserCommand {

    private String name;    //公司名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
