package pengyi.core.type;

/**
 * Created by YJH on 2016/3/8.
 */
public enum OrderStatus {

    WAIT_ORDER("待接单", 1, Boolean.FALSE),
    HAS_ORDER("已接单", 2, Boolean.FALSE),
    START_ORDER("已开始", 3, Boolean.FALSE),
    WAIT_PAY("待支付", 4, Boolean.FALSE),
    SUCCESS("完成", 5, Boolean.FALSE),
    INVALID("作废", 6, Boolean.FALSE);

    private OrderStatus(String name, int value, Boolean onlyQuery) {
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
