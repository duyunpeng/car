package pengyi.interfaces.withhold.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.withhold.IWithholdAppService;
import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.application.withhold.representation.WithholdRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by pengyi on 2016/5/6.
 */
@Controller
@RequestMapping("/withhold")
public class WithholdController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWithholdAppService withholdAppService;

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        WithholdRepresentation withhold = null;

        try {
            withhold = withholdAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/withhold/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/withhold/show", "withhold", withhold);
    }

    @RequestMapping(value = "/create/{userId}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable("userId") String userId, @ModelAttribute("command") CreateWithholdCommand command) {
        return new ModelAndView("/withhold/create", "command", command).addObject("userId", userId);
    }

    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST)
    public ModelAndView create(@PathVariable("userId") String userId, @Valid @ModelAttribute("command") CreateWithholdCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale, HttpSession session) {
        BaseResponse response = null;
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        command.setLoginUser(user.getId());
        command.setUserId(userId);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/withhold/create", "command", command).addObject("userId", userId);
        }
        AlertMessage alertMessage = null;

        try {
            withholdAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/withhold/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/withhold/list");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(ListWithholdCommand command) {
        return new ModelAndView("/withhold/list", "pagination", withholdAppService.pagination(command))
                .addObject("command", command);
    }

}
