package pengyi.core.type;

/**
 * Created by YJH on 2016/5/3.
 */
public enum AuthStatus {

    ALL("全部", 0, Boolean.TRUE),
    AUTH_WAIT("待审核", 1, Boolean.FALSE),
    AUTH_COMPANY("公司审核通过", 2, Boolean.FALSE),
    AUTH_TERRACE("平台审核通过", 3, Boolean.FALSE);

    private AuthStatus(String name, int value, Boolean onlyQuery) {
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
