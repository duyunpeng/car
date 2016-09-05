package pengyi.core.exception;

/**
 *
 * 不相等异常
 *
 * Created by YJH on 2016/3/7.
 */
public class NotEqualException extends RuntimeException {

    public NotEqualException() {
    }

    public NotEqualException(String message) {
        super(message);
    }

    public NotEqualException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualException(Throwable cause) {
        super(cause);
    }

}
