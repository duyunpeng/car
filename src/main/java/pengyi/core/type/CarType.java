package pengyi.core.type;

/**
 * Created by lvdi on 2016/3/21.
 */
public enum CarType {

    ECONOMY("经济型", 1, Boolean.FALSE),
    COMFORT("舒适型", 2, Boolean.FALSE),
    BUSINESS("商务型", 3, Boolean.FALSE),
    LUXURY("豪华型", 4, Boolean.FALSE);

    private CarType(String name, int value, Boolean onlyQuery) {
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
