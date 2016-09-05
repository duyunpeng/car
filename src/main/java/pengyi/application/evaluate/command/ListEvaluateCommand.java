package pengyi.application.evaluate.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class ListEvaluateCommand extends BasicPaginationCommand {

    private String evaluateUser;                  //评价人
    private String order;                         //订单
    private String content;                           //评价内容
    private int level;                                //评级
    private String createDate;                       //评价时间

    public String getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(String evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
