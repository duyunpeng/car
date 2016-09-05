package pengyi.application.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.driver.command.*;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.core.redis.RedisService;
import pengyi.core.type.DriverType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiDriverAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiDriverAppService implements IApiDriverAppService {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseResponse companyDriverList(CompanyDriverListCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }

            Pagination<Driver> pagination = driverService.apiPagination(command);
            List<DriverRepresentation> data = mappingService.mapAsList(pagination.getData(), DriverRepresentation.class);
            Pagination<DriverRepresentation> result = new Pagination<DriverRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse companyEditDriver(CompanyDriverEditCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getIdentityCardPic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10025.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getDrivingLicencePic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10026.getMessage());
            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                //如果注册用户是专车
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            } else if (command.getDriverType() == DriverType.GENERATION) {
                //如果注册用户是代驾
                if (CoreStringUtils.isEmpty(command.getDrivingLicenceType())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10043.getMessage());
                }
            } else {
                //如果注册用户是出租车
                if (CoreStringUtils.isEmpty(command.getBusinessPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10044.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getWorkPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10045.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            }
            DriverRepresentation driver = mappingService.map(driverService.apiCompanyEditDriver(command), DriverRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, driver, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse companyAuditingDriver(CompanyAuditingDriverCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }

            driverService.apiCompanyAuditingDriver(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse companyEditStatusDriver(EditStatusCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null != command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            driverService.apiCompanyEditStatusDriver(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse companyCreateDriver(CreateDriverCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10011.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getIdentityCardPic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10025.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getDrivingLicencePic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10026.getMessage());
            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                //如果注册用户是专车
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            } else if (command.getDriverType() == DriverType.GENERATION) {
                //如果注册用户是代驾
                if (CoreStringUtils.isEmpty(command.getDrivingLicenceType())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10043.getMessage());
                }
            } else {
                //如果注册用户是出租车
                if (CoreStringUtils.isEmpty(command.getBusinessPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10044.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getWorkPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10045.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            }
            DriverRepresentation driver = mappingService.map(driverService.apiCompanyCreateDriver(command), DriverRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, driver, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse companyExpelDriver(EditStatusCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null != command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            driverService.apiCompanyExpelDriver(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse register(RegisterDriverCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getUserName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10010.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getPassword())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10011.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getIdentityCardPic())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10025.getMessage());
            }
//            if (CoreStringUtils.isEmpty(command.getDrivingLicencePic())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10026.getMessage());
//            }
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getVerificationCode())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10019.getMessage());
            }
//            if (CoreStringUtils.isEmpty(command.getName())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10040.getMessage());
//            }
//            if (CoreStringUtils.isEmpty(command.getPhone())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10041.getMessage());
//            }
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
//            if (command.getDriverType() == DriverType.LIMOUSINE) {
//                //如果注册用户是专车
//                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
//                }
//            } else if (command.getDriverType() == DriverType.GENERATION) {
//                //如果注册用户是代驾
//                if (CoreStringUtils.isEmpty(command.getDrivingLicenceType())) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10043.getMessage());
//                }
//            } else {
//                //如果注册用户是出租车
//                if (CoreStringUtils.isEmpty(command.getBusinessPic())) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10044.getMessage());
//                }
//                if (CoreStringUtils.isEmpty(command.getWorkPic())) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10045.getMessage());
//                }
//                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
//                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
//                }
//            }
//            if (CoreStringUtils.isEmpty(command.getStartDriveDate())) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
//            }
            if (redisService.exists(command.getUserName())) {
                if (!redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR, 0, null,
                            ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR.getMessage());
                }
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND, 0, null,
                        ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND.getMessage());
            }
            DriverRepresentation driver = mappingService.map(driverService.apiRegister(command), DriverRepresentation.class, false);
            redisService.delete(command.getUserName());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse show(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        DriverRepresentation data = mappingService.map(driverService.show(id), DriverRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse edit(EditDriverCommand command) {
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
            if (null == command.getDriverType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10008.getMessage());
            }
            if (command.getDriverType() == DriverType.LIMOUSINE) {
                //如果注册用户是专车
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            } else if (command.getDriverType() == DriverType.GENERATION) {
                //如果注册用户是代驾
                if (CoreStringUtils.isEmpty(command.getDrivingLicenceType())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10043.getMessage());
                }
            } else {
                //如果注册用户是出租车
                if (CoreStringUtils.isEmpty(command.getBusinessPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10044.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getWorkPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10045.getMessage());
                }
                if (CoreStringUtils.isEmpty(command.getTravelPic())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10042.getMessage());
                }
            }
            DriverRepresentation driver = mappingService.map(driverService.apiEdit(command), DriverRepresentation.class, false);
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
            DriverRepresentation driver = mappingService.map(driverService.apiUpdateHeadPic(command), DriverRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

}
