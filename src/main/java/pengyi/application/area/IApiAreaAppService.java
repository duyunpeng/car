package pengyi.application.area;

import pengyi.application.area.command.SearchAreaListCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/16.
 */
public interface IApiAreaAppService {
    BaseResponse apiSearchAreaList(SearchAreaListCommand command);
}
