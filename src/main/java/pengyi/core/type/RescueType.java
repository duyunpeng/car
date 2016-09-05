package pengyi.core.type;

/**
 * Created by YJH on 2016/3/23.
 */
public enum RescueType {
    CHILDREN("留守儿童", 1, Boolean.FALSE),
    OLD_MAN("空巢老人", 2, Boolean.FALSE),
    WOMAN("留守妇女", 3, Boolean.FALSE),
    OTHER("SOS紧急", 4, Boolean.FALSE);

    RescueType(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private String name;

    private int value;

    private Boolean onlyQuery;                  // 仅用于页面查询和业务逻辑无关

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean getOnlyQuery() {
        return onlyQuery;
    }

    public void setOnlyQuery(Boolean onlyQuery) {
        this.onlyQuery = onlyQuery;
    }
}
