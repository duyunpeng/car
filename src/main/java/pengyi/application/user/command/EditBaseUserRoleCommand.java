package pengyi.application.user.command;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditBaseUserRoleCommand {

    private String id;
    private Integer version;
    private String userRole;

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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
