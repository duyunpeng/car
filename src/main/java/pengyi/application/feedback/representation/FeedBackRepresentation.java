package pengyi.application.feedback.representation;

import pengyi.core.type.HandleStatus;

import java.util.Date;

/**
 * Created by YJH on 2016/3/9.
 */
public class FeedBackRepresentation {

    private String id;
    private Integer version;

    private String email;       //联系邮箱
    private String phone;       //联系电话
    private String qq;          //联系QQ
    private String content;     //意见内容
    private Date createDate;    //创建时间
    private HandleStatus status;//处理状态

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public HandleStatus getStatus() {
        return status;
    }

    public void setStatus(HandleStatus status) {
        this.status = status;
    }
}
