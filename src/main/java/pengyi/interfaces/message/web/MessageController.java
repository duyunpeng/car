package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.message.IMessageAppService;
import pengyi.application.message.command.CreateMessageByRoleCommand;
import pengyi.application.message.command.DeleteMessageCommand;
import pengyi.application.message.command.EditMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.application.role.IRoleAppService;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ConcurrencyException;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by liubowen on 2016/3/11.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired//注入appService
    private IMessageAppService messageAppService;

    @Autowired
    private IRoleAppService roleAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListMessageCommand command) {
        return new ModelAndView("/message/list", "command", command)
                .addObject("pagination", messageAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateMessageByRoleCommand command) {
        List<RoleRepresentation> roles = roleAppService.roleList();
        return new ModelAndView("/message/create", "command", command).addObject("roles", roles);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateMessageByRoleCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        command.setSendBaseUser(user.getId());
        List<RoleRepresentation> roles = roleAppService.roleList();
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/message/create", "command", command).addObject("roles", roles);
        }
        AlertMessage alertMessage = null;

        try {
            messageAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/message/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage).addObject("roles", roles);
        }
        logger.info("创建站内信息成功,时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/message/list");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MessageRepresentation representation;
        try {
            representation = messageAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:message/list");
        }
        return new ModelAndView("/message/show", "message", representation);
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable String id,
                               RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MessageRepresentation message = null;

        try {
            message = messageAppService.delete(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("/message/list");
        }
        logger.info("将messageId=[" + message.getId() + "]标记不显示，时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/message/list");


    }



}
