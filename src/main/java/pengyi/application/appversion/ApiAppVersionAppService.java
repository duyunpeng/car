package pengyi.application.appversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.application.appversion.representation.AppVersionRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.EnableStatus;
import pengyi.domain.service.appversion.IAppVersionService;

/**
 * Created by YJH on 2016/4/13.
 */
@Service("apiAppVersionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAppVersionAppService implements IApiAppVersionAppService {

    @Autowired
    private IAppVersionService appVersionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse searchNewVersion() {
        AppVersionRepresentation data = mappingService.map(appVersionService.searchNewVersion(), AppVersionRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

}
