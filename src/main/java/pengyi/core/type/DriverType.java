package pengyi.core.type;

/**
 * Created by YJH on 2016/3/8.
 */
public enum  DriverType {

    GENERATION("代驾", 1, Boolean.FALSE),
    LIMOUSINE("专车", 2, Boolean.FALSE),
    TAXI("出租车", 3, Boolean.FALSE);

    private DriverType(String name, int value, Boolean onlyQuery) {
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

    public Integer getValue() {
        return value;
    }

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }

}
