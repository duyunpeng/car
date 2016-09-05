package pengyi.interfaces.permission.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.permission.IPermissionAppService;
import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.role.IApiRoleAppService;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/permission/api")
public class ApiPermissionController {

}
