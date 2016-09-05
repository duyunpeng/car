package pengyi.domain.service.order;

import pengyi.application.order.command.UploadWayCommand;
import pengyi.domain.model.order.OrderWayPoint;

import java.util.List;

/**
 * Created by pengyi on 2016/4/27.
 */
public interface IOrderWayPointService {

    List<OrderWayPoint> list(String orderId);

    void upload(UploadWayCommand command);
}
