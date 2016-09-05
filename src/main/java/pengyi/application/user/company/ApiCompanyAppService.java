package pengyi.application.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.application.user.company.representation.ApiAppCommpanyRepresentaction;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.service.user.company.ICompanyService;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiCompanyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiCompanyAppService implements IApiCompanyAppService {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse apiInfo(String id) {
        CompanyRepresentation company = mappingService.map(companyService.show(id), CompanyRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, company, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse apiEdit(EditCompanyCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10002.getMessage());
            }
//            if (CoreStringUtils.isEmpty(command.getRegisterAddress())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10003.getMessage());
//            }
//            if (CoreStringUtils.isEmpty(command.getOperateAddress())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10004.getMessage());
//            }
            CompanyRepresentation company = mappingService.map(companyService.apiEdit(command), CompanyRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, company, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiUpdateFolder(UpdateFolderCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (!CoreStringUtils.isEmpty(command.getFolder())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10005.getMessage());
            }
            companyService.apiUpdateFolder(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiCreateCompany(CreateCompanyCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10011.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getEmail())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10013.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10002.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getFolder())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10005.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getRegisterDate())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10014.getMessage());
            } else if (null == CoreDateUtils.parseDate(command.getRegisterDate())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10015.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getRegisterAddress())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10003.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getOperateAddress())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10004.getMessage());
            }
            CompanyRepresentation company = mappingService.map(companyService.apiCreate(command), CompanyRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, company, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiUpdatePassword(UpdatePasswordCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getOldPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10016.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getOldPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10017.getMessage());
            }
            Company company = companyService.apiUpdatePassword(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiResetPassword(ResetPasswordCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10011.getMessage());
            }
            Company company = companyService.apiResetPassword(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiList() {
        BaseListCompanyCommand companyCommand = new BaseListCompanyCommand();
        companyCommand.setStatus(EnableStatus.ENABLE);
        List<ApiAppCommpanyRepresentaction> company = mappingService.mapAsList(companyService.list(companyCommand), ApiAppCommpanyRepresentaction.class);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, company, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }
}
