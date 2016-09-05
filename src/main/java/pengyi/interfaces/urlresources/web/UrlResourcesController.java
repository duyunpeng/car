package pengyi.interfaces.urlresources.web;

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
import pengyi.application.urlresources.IUrlResourcesAppService;
import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.command.EditUrlResourcesCommand;
import pengyi.application.urlresources.command.ListUrlResourcesCommand;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping(value = "/url_resources")
public class UrlResourcesController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUrlResourcesAppService urlResourcesAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListUrlResourcesCommand command) {
        return new ModelAndView("/urlresources/list", "command", command)
                .addObject("pagination", urlResourcesAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateUrlResourcesCommand command) {
        return new ModelAndView("/urlresources/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateUrlResourcesCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/urlresources/create", "command", command);
        }

        AlertMessage alertMessage;
        UrlResourcesRepresentation urlResources = null;

        try {
            urlResources = urlResourcesAppService.create(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/urlresources/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建访问资源路径成功id=[" + urlResources.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", urlResources.getId());
        return new ModelAndView("redirect:/url_resources/show/{id}");
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        UrlResourcesRepresentation urlResources = null;
        try {
            urlResources = urlResourcesAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/url_resources/list");
        }

        return new ModelAndView("/urlresources/show", "urlResources", urlResources);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditUrlResourcesCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;

        UrlResourcesRepresentation urlResources = null;

        try {
            urlResources = urlResourcesAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/url_resources/list");
        }

        return new ModelAndView("/urlresources/edit", "command", command)
                .addObject("urlResources", urlResources);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditUrlResourcesCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/urlresources/edit", "command", command);
        }

        AlertMessage alertMessage;
        UrlResourcesRepresentation urlResources = null;

        try {
            urlResources = urlResourcesAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/url_resources/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/urlresources/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改访问资源路径成功id=[" + urlResources.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", urlResources.getId());

        return new ModelAndView("redirect:/url_resources/show/{id}");

    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        UrlResourcesRepresentation urlResources = null;
        try {
            urlResources = urlResourcesAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/url_resources/list");
        }

        logger.info("修改访问路径状态成功id=[" + urlResources.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/url_resources/list");
    }

}
