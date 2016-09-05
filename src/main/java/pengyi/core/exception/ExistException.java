package pengyi.core.exception;

/**
 *
 * 数据已存在异常
 *
 * Created by YJH on 2016/3/7.
 */
public class ExistException extends RuntimeException {

    public ExistException() {
    }

    public ExistException(String message) {
        super(message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistException(Throwable cause) {
        super(cause);
    }

}
