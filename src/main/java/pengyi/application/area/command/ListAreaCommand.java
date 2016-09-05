package pengyi.application.area.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListAreaCommand extends BasicPaginationCommand {

    private String name;                    //地区名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
