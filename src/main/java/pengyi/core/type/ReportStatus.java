package pengyi.core.type;

/**
 * Created by liubowen on 2016/3/8.
 */
public enum ReportStatus {

    PENDING("待处理",0,Boolean.FALSE),
    IN_PROCESS("正在处理",1,Boolean.FALSE),
    FIGURE_OUT("处理完成",2,Boolean.FALSE);
    private String name;
    private int value;
    private Boolean onlyQuery;

    ReportStatus(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

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

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }
}
