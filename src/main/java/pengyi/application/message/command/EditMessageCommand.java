package pengyi.application.message.command;

/**
 * Created by liubowen on 2016/3/9.
 */
public class EditMessageCommand {

    private String id;
    private Integer version;
    private String receiveDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
