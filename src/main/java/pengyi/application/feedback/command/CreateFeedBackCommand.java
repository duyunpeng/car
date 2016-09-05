package pengyi.application.feedback.command;

/**
 * Created by YJH on 2016/3/9.
 */
public class CreateFeedBackCommand {

    private String email;       //联系邮箱
    private String phone;       //联系电话
    private String qq;          //联系QQ
    private String content;     //意见内容

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
