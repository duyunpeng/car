package pengyi.application.user.command;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseEditBaseUserCommand {

    private String id;
    private Integer version;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
