package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseListBaseUserCommand;

/**
 * Created by YJH on 2016/3/11.
 */
public class BaseListDriverCommand extends BaseListBaseUserCommand {

    private String name;    //司机姓名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
