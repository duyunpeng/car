package pengyi.socketserver.model;

import pengyi.core.type.UserType;

/**
 * 接收客户端
 * Created by pengyi on 2016/3/10.
 */
public class ReceiveObj {

    private UserType type;                  //用户类型
    private String phone;                 //userId

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ReceiveObj() {
    }

    public ReceiveObj(UserType type, String phone) {
        this.type = type;
        this.phone = phone;
    }
}
