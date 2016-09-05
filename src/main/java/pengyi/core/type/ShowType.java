package pengyi.core.type;

/**
 * Created by liubowen on 2016/3/15.
 */
public enum  ShowType {
    SHOW("显示", 0, Boolean.FALSE),
    BLANK("不显示", 1, Boolean.FALSE);


    private ShowType(String name, int value, Boolean onlyQuery) {
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
