package pengyi.interfaces.area.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.area.IAreaAppService;
import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.NoFoundException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.interfaces.shared.web.JsonMessage;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/3/20 0020.
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAreaAppService areaAppService;

    @RequestMapping(value = "/list")
    public ModelAndView pagination(ListAreaCommand command) {
        return new ModelAndView("/area/list", "pagination", areaAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes) {
        AlertMessage alertMessage;
        AreaRepresentation area = null;
        try {
            area = areaAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/area/list");
        }
        return new ModelAndView("/area/show", "area", area);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateAreaCommand command) {
        return new ModelAndView("/area/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateAreaCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/area/create", "command", command);
        }
        AlertMessage alertMessage;
        AreaRepresentation area = null;
        try {
            area = areaAppService.create(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/area/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", area.getId());
        return new ModelAndView("redirect:/area/show/{id}");
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditAreaCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        AreaRepresentation area = null;
        try {
            area = areaAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/area/list");
        }

        return new ModelAndView("/area/edit", "area", area).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditAreaCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/area/edit", "command", command);
        }
        AlertMessage alertMessage;
        AreaRepresentation area = null;
        try {
            area = areaAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/area/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/area/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", area.getId());

        return new ModelAndView("redirect:/area/show/{id}");
    }

    @RequestMapping(value = "/search_by_parent")
    @ResponseBody
    public JsonMessage searchByParent(String parentId) {
        JsonMessage jsonMessage = new JsonMessage();
        try {
            List<AreaRepresentation> data = areaAppService.searchByParent(parentId);
            jsonMessage.setData(data);
            jsonMessage.setMessage("请求成功");
            jsonMessage.setCode("0");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            jsonMessage.setData(null);
            jsonMessage.setCode("1");
            jsonMessage.setMessage("请求失败");
        }
        return jsonMessage;
    }

}
