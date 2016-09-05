package pengyi.application.recharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.application.recharge.command.ListRechargeCommand;
import pengyi.application.recharge.representation.RechargeRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.model.recharge.Recharge;
import pengyi.domain.service.recharge.IRechargeService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/4/11.
 */
@Service("rechargeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RechargeAppService implements IRechargeAppService {

    @Autowired
    private IRechargeService rechargeService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse wechatPay(CreateRechargeCommand command) {
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, rechargeService.wechatPay(command), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse alipayPay(CreateRechargeCommand command) {
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, mappingService.map(rechargeService.alipayPay(command),
                RechargeRepresentation.class, false), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public void alipaySuccess(AlipayNotify notify) {
        rechargeService.alipaySuccess(notify);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {
        rechargeService.wechatSuccess(notify);
    }

    @Override
    public Pagination<RechargeRepresentation> pagination(ListRechargeCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Recharge> pagination = rechargeService.pagination(command);
        List<RechargeRepresentation> data = mappingService.mapAsList(pagination.getData(), RechargeRepresentation.class);
        return new Pagination<RechargeRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }
}
