package pengyi.core.api;

/**
 * 返回码
 * Created by pengyi on 2016/1/18.
 */
public enum ResponseCode {

    RESPONSE_CODE_SUCCESS(10000, "成功"),
    RESPONSE_CODE_FAILURE(90000, "未知错误"),

    RESPONSE_CODE_CONCURRENCY_ERROR(90001, "记录在提交之前已发生改变,请重新提交."),
    RESPONSE_CODE_PARAMETER_ERROR(90002, "参数错误"),
    RESPONSE_CODE_UNKNOWN_ACCOUNT(90003, "用户不存在"),
    RESPONSE_CODE_ERROR_ACCOUNT_PASSWORD(90004, "用户名或密码错误"),
    RESPONSE_CODE_LOCK_ACCOUNT(90005, "账户被禁用"),
    RESPONSE_CODE_NOT_LOGIN(90006,"未登录"),
    RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE(90007,"验证码未过期"),
    RESPONSE_CODE_ACCOUNT_EXIST(90008,"电话号码已被注册"),
    RESPONSE_CODE_VERIFICATION_CODE_ERROR(90009,"验证码错误"),
    RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND(90010,"未发送验证码"),
    RESPONSE_CODE_ORDER_UPDATED(90011,"订单已更新"),
    RESPONSE_CODE_NOT_SUFFICIENT_FUNDS(90012,"余额不足"),
    RESPONSE_CODE_HAS_ORDER(900013, "有未完成的订单");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
