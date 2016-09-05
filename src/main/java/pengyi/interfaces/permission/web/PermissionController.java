package pengyi.interfaces.permission.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.permission.IPermissionAppService;
import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.permission.command.EditPermissionCommand;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.repository.generic.Pagination;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/10.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPermissionAppService permissionAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListPermissionCommand command) {
        return new ModelAndView("/permission/list", "command", command)
                .addObject("pagination", permissionAppService.pagination(command));
    }

    @RequestMapping(value = "/permission_list")
    @ResponseBody
    public Pagination<PermissionRepresentation> permissionList(@RequestBody ListPermissionCommand command){
        return permissionAppService.permissionList(command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreatePermissionCommand command) {
        return new ModelAndView("permission/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreatePermissionCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/permission/create", "command", command);
        }

        AlertMessage alertMessage = null;

        PermissionRepresentation permission = null;

        try {
            permission = permissionAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/permission/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建权限成功id=[" + permission.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", permission.getId());
        return new ModelAndView("redirect:/permission/show/{id}");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        PermissionRepresentation permission = null;
        try {
            permission = permissionAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/permission/list");
        }
        return new ModelAndView("/permission/show", "permission", permission);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditPermissionCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        PermissionRepresentation permission = null;

        try {
            permission = permissionAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/permission/list");
        }

        return new ModelAndView("/permission/edit", "command", command).addObject("permission", permission);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditPermissionCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/permission/edit", "command", command);
        }

        AlertMessage alertMessage;
        PermissionRepresentation permission = null;

        try {
            permission = permissionAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/permission/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/permission/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改权限成功id=[" + permission.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", permission.getId());

        return new ModelAndView("redirect:/permission/show/{id}");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale){
        AlertMessage alertMessage;
        PermissionRepresentation permission = null;
        try {
            permission = permissionAppService.updateStatus(command);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/permission/list");
        }

        logger.info("修改权限状态成功id=[" + permission.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message",null,locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
        return new ModelAndView("redirect:/permission/list");
    }

}
