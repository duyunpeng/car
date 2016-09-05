package pengyi.application.pay;

import pengyi.core.api.BaseResponse;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;

/**
 * Created by pengyi on 2016/4/1.
 */
public interface IPayAppService {

    void alipaySuccess(AlipayNotify notify);

    void wechatSuccess(WechatNotify notify);

    BaseResponse wechatPay(String orderId, String ipAddress);
}
