package pengyi.application.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.RegisterUserCommand;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.service.user.user.IUserService;

/**
 * Created by YJH on 2016/3/21.
 */
@Service("apiUserAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiUserAppService implements IApiUserAppService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseResponse show(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        UserRepresentation data = mappingService.map(userService.show(id), UserRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse edit(EditUserCommand command) {
        if (null != command) {
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
//            if (CoreStringUtils.isEmpty(command.getEmail())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10013.getMessage());
//            }
            if (CoreStringUtils.isEmpty(command.getName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10002.getMessage());
            }
            if (null == command.getSex()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10007.getMessage());
            }
            UserRepresentation data = mappingService.map(userService.edit(command), UserRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse updateHeadPic(UpdateHeadPicCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getHeadPic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10018.getMessage());
            }
            UserRepresentation user = mappingService.map(userService.apiUpdateHeadPic(command), UserRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse register(RegisterUserCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10011.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getVerificationCode())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10019.getMessage());
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
            UserRepresentation user = mappingService.map(userService.apiRegister(command), UserRepresentation.class, false);
            redisService.delete(command.getUserName());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

}
