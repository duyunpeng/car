package pengyi.core.type;

/**
 * Created by YJH on 2016/3/8.
 */
public enum EvaluateStatus {

    NOT_EVALUATE("未评价", 1, Boolean.FALSE),
    DRIVER_EVALUATE("司机已评价", 2, Boolean.FALSE),
    USER_EVALUATE("用户已评价", 3, Boolean.FALSE),
    OK_EVALUATE("以互相评价", 4, Boolean.FALSE);

    private EvaluateStatus(String name, int value, Boolean onlyQuery) {
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
