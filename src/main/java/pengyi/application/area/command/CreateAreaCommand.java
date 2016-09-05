package pengyi.application.area.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/9.
 */
public class CreateAreaCommand {

    @NotEmpty(message = "{area.name.NotEmpty.message}")
    private String name;                    //地区名
    @NotEmpty(message = "{area.priority.NotEmpty.message}")
    private String priority;                //区域优先级
    private String parent;                    //父级地区

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
