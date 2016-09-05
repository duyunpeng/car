package pengyi.core.exception;

/**
 * 余额不足异常
 * Created by YJH on 2016/4/6.
 */
public class NotSufficientFundsException extends RuntimeException {
    public NotSufficientFundsException() {
        super();
    }

    public NotSufficientFundsException(String message) {
        super(message);
    }

    public NotSufficientFundsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotSufficientFundsException(Throwable throwable) {
        super(throwable);
    }
}
