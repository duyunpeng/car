package pengyi.application.order;

import pengyi.application.order.command.UploadWayCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by pengyi on 2016/4/28.
 */
public interface IApiOrderWayPointAppService {

    BaseResponse list(String orderId);

    BaseResponse upload(UploadWayCommand command);
}
