package pengyi.application.area.command;

/**
 * Created by YJH on 2016/3/16.
 */
public class SearchAreaListCommand {

    private String id;      //地区id
    private String name;    //地区名称
    private String parent;  //父级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
