package pengyi.domain.model.area;

import pengyi.domain.model.base.Identity;

/**
 * 区域表
 * Created by pengyi on 2016/3/4.
 */
public class Area extends Identity {

    private String name;                    //地区名
    private String priority;                //区域优先级
    private Area parent;                    //父级地区

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

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    public Area() {
        super();
    }

    public Area(String name, String priority, Area parent) {
        super();
        this.name = name;
        this.priority = priority;
        this.parent = parent;
    }
}
