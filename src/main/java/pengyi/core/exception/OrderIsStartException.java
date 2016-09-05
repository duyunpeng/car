package pengyi.core.exception;

/**
 * 订单已经开始，尝试取消异常
 * <p/>
 * Created by YJH on 2016/3/22.
 */
public class OrderIsStartException extends RuntimeException {

    public OrderIsStartException() {
    }

    public OrderIsStartException(String message) {
        super(message);
    }

    public OrderIsStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderIsStartException(Throwable cause) {
        super(cause);
    }

}
