package pengyi.interfaces.urlresources.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.urlresources.IApiUrlResourcesAppService;
import pengyi.application.urlresources.IUrlResourcesAppService;
import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/url_resources/api")
public class ApiUrlResourcesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiUrlResourcesAppService apiUrlResourcesAppService;

    @RequestMapping(value = "/all_list")
    @ResponseBody
    public BaseResponse allList() {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            List<UrlResourcesRepresentation> urlResources = apiUrlResourcesAppService.allList();
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, urlResources, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
