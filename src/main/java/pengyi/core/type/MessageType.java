package pengyi.core.type;

/**
 * Created by liubowen on 2016/3/8.
 */
public enum MessageType {

    SYSTEM_MESSAGE("系统消息",0,Boolean.FALSE),
    OTHER_MESSAGE("其他消息",1,Boolean.FALSE);

    private String name;

    private int value;

    private Boolean onlyQuery;

    MessageType(String name, int value, Boolean onlyQuery) {
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
