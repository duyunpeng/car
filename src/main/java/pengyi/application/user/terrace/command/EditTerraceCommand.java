package pengyi.application.user.terrace.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditTerraceCommand extends BaseEditBaseUserCommand {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
