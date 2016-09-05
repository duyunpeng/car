package pengyi.interfaces.roport.web;

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
import pengyi.application.report.IReportAppService;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.NoFoundException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liubowen on 2016/3/11.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IReportAppService reportAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListReportCommand command) {
        return new ModelAndView("/report/list", "command", command)
                .addObject("pagination", reportAppService.pagination(command));
    }

   /* @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateReportCommand command) {
        return new ModelAndView("report/create", "commadn", command);
    }*/

   /* @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateReportCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/report/create", "command", command);
        }
        AlertMessage alertMessage = null;

        ReportRepresentation representation = null;

        try {
            representation = reportAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/report/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("举报成功id[" + representation.getId() + "]时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", representation.getId());
        return new ModelAndView("redirect:/report/show/{id}");

    }*/

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        ReportRepresentation representation = null;
        try {
            representation = reportAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/report/list");
        }
        return new ModelAndView("/report/show", "report", representation);
    }

    // @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    /*public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditReportCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        ReportRepresentation representation = null;
        try {
            representation = reportAppService.show(id);

        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/report.list");

        }
        return new ModelAndView("report/edit", "command", command);
    }*/

    //@RequestMapping(value = "/edit", method = RequestMethod.POST)
    /*public ModelAndView edit(@Valid @ModelAttribute("command") EditReportCommand command, BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/report/edit", "command", command);

        }
        AlertMessage alertMessage;
        ReportRepresentation representation;
        try {
            representation = reportAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/report/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/report/edit", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("修改成功id=[" + representation.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", representation.getId());
        return new ModelAndView("redirect:/report/show{id}");
    }*/

    @RequestMapping(value = "/handle_report")
    public ModelAndView handleReport(EditStatusCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            reportAppService.handleReport(command);
        } catch (NoFoundException e) {

        } catch (ConcurrencyException e) {

        } catch (Exception e) {

        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/list");
    }

    @RequestMapping(value = "/success_report")
    public ModelAndView successReport(EditReportCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            reportAppService.successReport(command);
        } catch (NoFoundException e) {

        } catch (ConcurrencyException e) {

        } catch (Exception e) {

        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/list");
    }


}
