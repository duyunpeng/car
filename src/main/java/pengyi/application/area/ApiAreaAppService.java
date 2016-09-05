package pengyi.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.command.SearchAreaListCommand;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.area.IAreaService;

import java.util.List;

/**
 * Created by YJH on 2016/3/16.
 */
@Service("apiAreaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAreaAppService implements IApiAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse apiSearchAreaList(SearchAreaListCommand command) {
        List<AreaRepresentation> area = mappingService.mapAsList(areaService.apiSearchAreaList(command), AreaRepresentation.class);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, area, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

}
