package pengyi.application.user.driver.command;

/**
 * Created by YJH on 2016/3/15.
 */
public class CompanyAuditingDriverCommand {

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
