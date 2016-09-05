package pengyi.application.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.service.pay.IPayService;


/**
 * Created by pengyi on 2016/4/1.
 */
@Service("payAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PayAppService implements IPayAppService {

    @Autowired
    private IPayService payService;

    @Override
    public void alipaySuccess(AlipayNotify notify) {
        payService.alipaySuccess(notify);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {
        payService.wechatSuccess(notify);
    }

    @Override
    public BaseResponse wechatPay(String orderId, String ipAddress) {
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, payService.wechatPay(orderId, ipAddress), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

}
