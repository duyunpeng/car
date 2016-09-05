package pengyi.core.type;

/**
 * Created by LvDi on 2016/3/9.
 */
public enum  RescueStatus {
    WAIT_RESCUE("待救援", 1, Boolean.FALSE),
    IN_RESCUE("救援中", 2, Boolean.FALSE),
    WAIT_AUDIT("完成待审核",3, Boolean.FALSE),
    SUCCESS_RESCUE("救援完成",4,Boolean.FALSE),
    CANCEL_RESCUE("取消救援",5,Boolean.FALSE);


    private RescueStatus(String name, int value, Boolean onlyQuery) {
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
