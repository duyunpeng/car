package pengyi.application.billing.command;

/**
 * Created by dyp on 2016/5/26.
 */
public class SharedCommand {
    private String id;
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
