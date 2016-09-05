package pengyi.application.user.terrace.representation;

import pengyi.application.user.representation.BaseUserRepresentation;

/**
 * Created by YJH on 2016/3/7.
 */
public class TerraceRepresentation extends BaseUserRepresentation {

    private String name;            //用户名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
