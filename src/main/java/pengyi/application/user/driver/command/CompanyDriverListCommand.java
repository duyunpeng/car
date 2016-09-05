package pengyi.application.user.driver.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.AuthStatus;
import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/15.
 */
public class CompanyDriverListCommand extends BasicPaginationCommand {

    private String company;

    private EnableStatus status;

    private String userName;

    private AuthStatus authStatus;

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
