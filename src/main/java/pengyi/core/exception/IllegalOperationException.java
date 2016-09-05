package pengyi.core.exception;

/**
 * 非法操作运行时异常
 * Created by YJH on 2016/3/18.
 */
public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IllegalOperationException(Throwable throwable) {
        super(throwable);
    }
}