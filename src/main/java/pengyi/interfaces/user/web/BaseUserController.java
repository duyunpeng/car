package pengyi.interfaces.user.web;

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
import pengyi.application.user.IBaseUserAppService;
import pengyi.application.user.command.BaseCreateBaseUserCommand;
import pengyi.application.user.command.BaseListBaseUserCommand;
import pengyi.application.user.command.EditBaseUserRoleCommand;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping("/base_user")
public class BaseUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBaseUserAppService baseUserAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(BaseListBaseUserCommand command) {
        return new ModelAndView("/baseuser/list", "command", command)
                .addObject("pagination", baseUserAppService.pagination(command));
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command,
                                     RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;
        try {
            baseUser = baseUserAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/base_user/list");
        }

        logger.info("修改用户状态成功id=[" + baseUser.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/base_user/list");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") BaseCreateBaseUserCommand command) {
        return new ModelAndView("/baseuser/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") BaseCreateBaseUserCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/create", "command", command);
        }

        AlertMessage alertMessage = null;

        BaseUserRepresentation user = null;

        try {
            user = baseUserAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/baseuser/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建user用户成功id=[" + user.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", user.getId());
        return new ModelAndView("redirect:/base_user/show/{id}");
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;
        try {
            baseUser = baseUserAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/base_user/list");
        }

        return new ModelAndView("/baseuser/show", "baseUser", baseUser);
    }

    @RequestMapping(value = "/authorize/{id}")
    public ModelAndView authorize(@PathVariable String id, EditBaseUserRoleCommand command,
                                  RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;
        try {
            baseUser = baseUserAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/base_user/list");
        }

        return new ModelAndView("/baseuser/authorize", "baseUser", baseUser).addObject("command", command);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ModelAndView authorize(@Valid @ModelAttribute("command") EditBaseUserRoleCommand command,
                                  RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;

        try {
            baseUser = baseUserAppService.updateBaseUserRole(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/base_user/list");
        }

        logger.info("修改用户id=[" + baseUser.getId() + "]角色成功,时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/base_user/list");
    }

    @RequestMapping(value = "/reset_password/{id}")
    public ModelAndView updatePassword(@PathVariable String id, ResetPasswordCommand command, RedirectAttributes redirectAttributes,
                                       Locale locale) {
        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;

        try {
            baseUser = baseUserAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/base_user/list");
        }

        return new ModelAndView("/baseuser/resetpassword", "command", command)
                .addObject("baseUser", baseUser);
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ModelAndView updatePassword(@Valid @ModelAttribute("command") ResetPasswordCommand command, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/resetpassword", "command", command);
        }

        AlertMessage alertMessage;
        BaseUserRepresentation baseUser = null;
        try {
            baseUser = baseUserAppService.resetPassword(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/base_user/reset_password/{id}");
        }

        logger.info("重置用户id=[" + baseUser.getId() + "]密码成功,时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/base_user/list");
    }

}
