package pengyi.application.recharge;

import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.application.recharge.command.ListRechargeCommand;
import pengyi.application.recharge.representation.RechargeRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.model.recharge.Recharge;
import pengyi.repository.generic.Pagination;

/**
 * Created by pengyi on 2016/4/11.
 */
public interface IRechargeAppService {

    BaseResponse wechatPay(CreateRechargeCommand command);

    BaseResponse alipayPay(CreateRechargeCommand command);

    void alipaySuccess(AlipayNotify notify);

    void wechatSuccess(WechatNotify notify);

    Pagination<RechargeRepresentation> pagination(ListRechargeCommand command);
}
