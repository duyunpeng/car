package pengyi.core.api;

/**
 * Created by pengyi on 2016/1/18.
 */
public class BaseResponse {

    private ResponseCode code;                                                                       //错误码
    private long debug_time;                                                                //后台处理时间
    private Object data;                                                                     //返回对象
    private String message;         //返回信息

    public BaseResponse() {
    }

    public BaseResponse(ResponseCode code, long debug_time, Object data, String message) {
        this.code = code;
        this.debug_time = debug_time;
        this.data = data;
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public long getDebug_time() {
        return debug_time;
    }

    public void setDebug_time(long debug_time) {
        this.debug_time = debug_time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
