package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.feedback.IApiFeedBackAppService;
import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;

/**
 * Created by YJH on 2016/3/30.
 */
@Controller
@RequestMapping("/api/app/feed_back")
public class ApiAppFeedBackController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiFeedBackAppService apiFeedBackAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse create(CreateFeedBackCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiFeedBackAppService.create(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
