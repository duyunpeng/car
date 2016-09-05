package pengyi.application.billing.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/21.
 */
public class ListBillingCommand extends BasicPaginationCommand {

    private String company;

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    private EnableStatus status;        //状态

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
