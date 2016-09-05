package pengyi.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pengyi.application.user.command.LoginUserCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.commons.id.IdFactory;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IdFactory idFactory;

    @RequestMapping(value = "/get_id")
    @ResponseBody
    public BaseResponse getId() {
        long startTime = System.currentTimeMillis();
        String id = "";
        try {
            id = idFactory.getNextId();
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, System.currentTimeMillis() - startTime, id, "成功");
        } catch (Exception e) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, System.currentTimeMillis() - startTime, id, e.getMessage());
        }

    }

    @RequestMapping(value = "/")
    public ModelAndView index(LoginUserCommand command, HttpSession session) {
        if (null != session.getAttribute(Constants.SESSION_USER)) {
            return new ModelAndView("redirect:/logined");
        }
        return new ModelAndView("/login", "user", command);
    }

    @RequestMapping(value = "/logined", method = RequestMethod.GET)
    public ModelAndView logined(HttpSession session) throws Exception {
        if (null != session.getAttribute(Constants.SESSION_USER)) {
            return new ModelAndView("/index");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/page404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView page404() throws Exception {
        return new ModelAndView("/errors/page404");
    }

    @RequestMapping(value = "/page500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView page500() throws Exception {
        return new ModelAndView("/errors/page500");
    }
}
