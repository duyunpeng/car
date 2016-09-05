package pengyi.application.withdraw.command;

/**
 * Created by pengyi on 2016/5/6.
 */
public class EditWithdrawCommand {

    private String id;
    private Integer version;
    private String loginUser;

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

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}
