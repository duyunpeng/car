package pengyi.application.user.terrace.command;

import pengyi.application.user.command.BaseListBaseUserCommand;

/**
 * Created by YJH on 2016/3/11.
 */
public class BaseListTerraceCommand extends BaseListBaseUserCommand {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
