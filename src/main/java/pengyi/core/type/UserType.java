package pengyi.core.type;

/**
 * Created by YJH on 2016/3/8.
 */
public enum UserType {

    TERRACE("平台", 1, Boolean.FALSE),
    USER("用户", 2, Boolean.FALSE),
    COMPANY("公司", 3, Boolean.FALSE),
    DRIVER("司机", 4, Boolean.FALSE);

    private UserType(String name, int value, Boolean onlyQuery) {
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
