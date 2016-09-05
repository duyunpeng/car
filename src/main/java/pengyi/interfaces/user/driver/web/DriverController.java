package pengyi.interfaces.user.driver.web;

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
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.application.user.driver.IDriverAppService;
import pengyi.application.user.driver.command.BaseListDriverCommand;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping("/user/driver")
public class DriverController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDriverAppService driverAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(BaseListDriverCommand command) {
        return new ModelAndView("/baseuser/driver/list", "command", command)
                .addObject("pagination", driverAppService.pagination(command));
    }

    @RequestMapping(value = "/auth_list")
    public ModelAndView auth_list(BaseListDriverCommand command) {
        command.setStatus(EnableStatus.DISABLE);
        return new ModelAndView("/baseuser/driver/authlist", "command", command)
                .addObject("pagination", driverAppService.pagination(command));
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, HttpServletRequest request, Locale locale) {
        String url = request.getHeader("referer");
        AlertMessage alertMessage;
        DriverRepresentation driver = null;
        try {
            driver = driverAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/driver/list");
        }
        if (url.indexOf("auth") != -1) {
            return new ModelAndView("/baseuser/driver/show", "driver", driver).addObject("returnPath", "/user/driver/auth_list");
        }
        return new ModelAndView("/baseuser/driver/show", "driver", driver).addObject("returnPath", "/user/driver/list");
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditDriverCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        DriverRepresentation driver = null;

        try {
            driver = driverAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/driver/list");
        }

        return new ModelAndView("/baseuser/driver/edit", "command", command).addObject("driver", driver);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditDriverCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/driver/edit", "command", command);
        }

        AlertMessage alertMessage;
        DriverRepresentation driver = null;

        try {
            driver = driverAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/user/driver/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/baseuser/driver/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改driver(司机)用户成功id=[" + driver.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", driver.getId());

        return new ModelAndView("redirect:/user/driver/show/{id}");
    }

    @RequestMapping(value = "/auth")
    public ModelAndView auth(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        DriverRepresentation driver = null;
        try {
            driver = driverAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("redirect:/user/driver/auth_list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改用户状态成功id=[" + driver.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/driver/auth_list");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(CreateDriverCommand command) {
        return new ModelAndView("/baseuser/driver/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateDriverCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/driver/create", "command", command);
        }
        AlertMessage alertMessage;
        DriverRepresentation driver;
        try {
            driver = driverAppService.create(command);
        } catch (ExistException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("user.userName.exist.message", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/baseuser/driver/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoFoundException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.create.failure.message", null, locale));
            return new ModelAndView("/baseuser/driver/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.create.failure.message", null, locale));
            return new ModelAndView("/baseuser/driver/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", driver.getId());
        return new ModelAndView("redirect:/user/driver/show/{id}");
    }

}
