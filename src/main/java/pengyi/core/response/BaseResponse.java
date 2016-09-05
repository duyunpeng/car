package pengyi.core.response;

/**
 * Created by pengyi on 2016/1/18.
 */
public class BaseResponse {

    private int code;
    private long debug_time;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getDebug_time() {
        return debug_time;
    }

    public void setDebug_time(long debug_time) {
        this.debug_time = debug_time;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public BaseResponse(int code, long debug_time, Object object) {
        this.code = code;
        this.debug_time = debug_time;
        this.object = object;
    }
}
