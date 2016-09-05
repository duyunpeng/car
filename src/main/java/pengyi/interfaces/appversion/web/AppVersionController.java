package pengyi.interfaces.appversion.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.appversion.IAppVersionAppService;
import pengyi.application.appversion.command.CreateAppVersionCommand;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.application.appversion.representation.AppVersionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/4/13.
 */
@Controller
@RequestMapping("/app_version")
public class AppVersionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAppVersionAppService appVersionAppService;

    @RequestMapping(value = "/list")
    public ModelAndView pagination(ListAppVersionCommand command) {
        return new ModelAndView("/appversion/list", "pagination", appVersionAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateAppVersionCommand command) {
        return new ModelAndView("/appversion/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateAppVersionCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/appversion/create", "command", command);
        }
        AlertMessage alertMessage;
        AppVersionRepresentation appversion = null;
        try {
            appversion = appVersionAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.create.failure.message", null, locale));
            return new ModelAndView("/appversion/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/app_version/list");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            appVersionAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/role/list");
        }

        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/app_version/list");
    }
}
