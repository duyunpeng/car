package pengyi.interfaces.evaluate.web;

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
import pengyi.application.evaluate.IEvaluateAppService;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LvDi on 2016/3/11.
 */
@Controller
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IEvaluateAppService evaluateAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListEvaluateCommand command) {
        return new ModelAndView("evaluate/list", "command", command).addObject("pagination", evaluateAppService.pagination(command));
    }

    /**
     * 发起评价
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateEvaluateCommand command) {
        return new ModelAndView("/evaluate/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateEvaluateCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/evaluate/create");
        }
        AlertMessage alertMessage = null;
        EvaluateRepresentation evaluateRepresentation = null;
        try {
             evaluateAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/evaluate/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("创建评论成功evaluateUser=[" + evaluateRepresentation.getOrder() +
                "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));

        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", evaluateRepresentation.getOrder());

        return new ModelAndView("redirect:/car/show/{id}");

    }

    /**
     * 查看评价
     */
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        EvaluateRepresentation evaluateRepresentation = null;
        try {
            evaluateRepresentation = evaluateAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/evaluate/list");
        }
        return new ModelAndView("/evaluate/show", "evaluate", evaluateRepresentation);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditEvaluateCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        EvaluateRepresentation evaluateRepresentation = null;
        try {
            evaluateRepresentation = evaluateAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/evaluate/list");
        }
        return new ModelAndView("/evaluate/edit", "command", command).addObject("evaluateRepresentation", evaluateRepresentation);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditEvaluateCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/evaluate/edit", "command", command);
        }

        AlertMessage alertMessage;
        EvaluateRepresentation evaluateRepresentation = null;

        try {
            evaluateAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/evaluate/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/evaluate/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改evaluate成功id=[" + evaluateRepresentation.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", evaluateRepresentation.getId());

        return new ModelAndView("redirect:/evaluate/show/{id}");


    }
}
