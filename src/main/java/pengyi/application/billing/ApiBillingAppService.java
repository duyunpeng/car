package pengyi.application.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.billing.command.*;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.DriverType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.service.billing.IBillingService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/22.
 */
@Service("apiBillingAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiBillingAppService implements IApiBillingAppService {

    @Autowired
    private IBillingService billingService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public BaseResponse showByCompany(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        Billing billing = billingService.searchByCompany(id);
        BillingRepresentation data = null;
        if (null != billing) {
            data = mappingService.map(billing, BillingRepresentation.class, false);
        }

        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse create(CreateBillingCommand command) {
        if (null != command) {
            if (null == command.getKmBilling()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10029.getMessage());
            }
            if (null == command.getStartKm()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10038.getMessage());
            }
            if (null == command.getMinuteBilling()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10030.getMessage());
            }
            if (null == command.getStartMin()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10039.getMessage());
            }
            if (null == command.getStartingPrice()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10035.getMessage());
            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                if (null == command.getCarType()) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30002.getMessage());
                }
            }
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            BillingRepresentation billing = mappingService.map(billingService.create(command), BillingRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse edit(EditBillingCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (null == command.getKmBilling()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10029.getMessage());
            }
            if (null == command.getStartKm()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10038.getMessage());
            }
            if (null == command.getMinuteBilling()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10030.getMessage());
            }
            if (null == command.getStartMin()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10039.getMessage());
            }
            if (null == command.getStartingPrice()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10035.getMessage());
            }
//            if (null == command.getDriverType()) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
//            }
//            if (command.getDriverType() == DriverType.LIMOUSINE) {
//                if (null == command.getCarType()) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30002.getMessage());
//                }
//            }
            BillingRepresentation billing = mappingService.map(billingService.edit(command), BillingRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse searchByDriver(SearchBillingCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                if (null == command.getCarType()) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30002.getMessage());
                }
            }
            List<Billing> billingList = billingService.searchByDriver(command);
            BillingRepresentation data = null;
            if (billingList.size() > 0) {
                data = mappingService.map(billingList.get(0), BillingRepresentation.class, false);
            }
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse apiPagination(ListBillingCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            Pagination<Billing> pagination = billingService.apiPagination(command);
            List<BillingRepresentation> data = mappingService.mapAsList(pagination.getData(), BillingRepresentation.class);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0,
                    new Pagination<BillingRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()),
                    ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse show(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        BillingRepresentation data = mappingService.map(billingService.show(id), BillingRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public void updateStatus(SharedCommand command) {
        billingService.updateStatus(command);
    }
}
