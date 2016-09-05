package pengyi.interfaces.role.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.role.IRoleAppService;
import pengyi.application.role.command.CreateRoleCommand;
import pengyi.application.role.command.EditRoleCommand;
import pengyi.application.role.command.ListRoleCommand;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.interfaces.shared.web.JsonMessage;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRoleAppService roleAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListRoleCommand command) {
        return new ModelAndView("/role/list", "command", command)
                .addObject("pagination", roleAppService.pagination(command));
    }

    @RequestMapping(value = "/all_list")
    @ResponseBody
    public JsonMessage roleList() {
        JsonMessage jsonMessage = new JsonMessage();
        try {
            jsonMessage.setData(roleAppService.roleList());
            jsonMessage.setCode("0");
            jsonMessage.setMessage("查询成功");
        } catch (Exception e) {
            jsonMessage.setData(null);
            jsonMessage.setCode("1");
            jsonMessage.setMessage("查询失败");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateRoleCommand command) {
        return new ModelAndView("/role/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateRoleCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/role/create", "command", command);
        }

        AlertMessage alertMessage = null;

        RoleRepresentation role = null;

        try {
            role = roleAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/role/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建角色成功id=[" + role.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", role.getId());
        return new ModelAndView("redirect:/role/show/{id}");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        RoleRepresentation role = null;
        try {
            role = roleAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/role/list");
        }
        return new ModelAndView("/role/show", "role", role);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditRoleCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        RoleRepresentation role = null;

        try {
            role = roleAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/role/list");
        }

        return new ModelAndView("/role/edit", "command", command).addObject("role", role);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditRoleCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/role/edit", "command", command);
        }

        AlertMessage alertMessage;
        RoleRepresentation role = null;

        try {
            role = roleAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/role/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/role/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改角色成功id=[" + role.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", role.getId());

        return new ModelAndView("redirect:/role/show/{id}");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        RoleRepresentation role = null;
        try {
            role = roleAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/role/list");
        }

        logger.info("修改角色状态成功id=[" + role.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/role/list");
    }

}
