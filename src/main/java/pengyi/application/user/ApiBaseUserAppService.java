package pengyi.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.command.BaseListBaseUserCommand;
import pengyi.application.user.command.LoginUserCommand;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiBaseUserAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiBaseUserAppService implements IApiBaseUserAppService {

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(readOnly = true)
    public BaseUserRepresentation apiSearchByUserName(String userName) {
        BaseUser baseUser = baseUserService.searchByUserName(userName);
        if (null != baseUser) {
            return mappingService.map(baseUser, BaseUserRepresentation.class, false);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public BaseUserRepresentation login(LoginUserCommand command) {
        return mappingService.map(baseUserService.login(command), BaseUserRepresentation.class, false);
    }

    @Override
    public BaseResponse resetPassword(ResetPasswordCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                        ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                        ResponseMessage.ERROR_10011.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getVerificationCode())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                        ResponseMessage.ERROR_10019.getMessage());
            }
            if (redisService.exists(command.getUserName())) {
                if (!redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR, 0, null,
                            ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR.getMessage());
                }
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND, 0, null,
                        ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND.getMessage());
            }
            BaseUserRepresentation data = mappingService.map(baseUserService.apiResetPassword(command), BaseUserRepresentation.class, false);
            redisService.delete(command.getUserName());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiSearchByUserNameAndRole(BaseListBaseUserCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getRoleName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10032.getMessage());
            }
            List<BaseUser> baseUsers = baseUserService.apiSearchByUserNameAndRole(command);
            BaseUserRepresentation data = null;
            if (baseUsers.size() > 0) {
                data = mappingService.map(baseUsers.get(0), BaseUserRepresentation.class, false);
            }
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }


}
