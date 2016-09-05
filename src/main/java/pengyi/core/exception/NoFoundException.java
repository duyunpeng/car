package pengyi.core.exception;

/**
 * 数据没有找到异常
 *
 * Created by YJH on 2016/3/7.
 */
public class NoFoundException extends RuntimeException {

    public NoFoundException() {
    }

    public NoFoundException(String message) {
        super(message);
    }

    public NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundException(Throwable cause) {
        super(cause);
    }

}
