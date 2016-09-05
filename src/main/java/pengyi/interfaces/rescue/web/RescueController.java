package pengyi.interfaces.rescue.web;

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
import pengyi.application.rescue.IRescueAppService;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LvDi on 2016/3/14.
 */
@Controller
@RequestMapping("/rescue")
public class RescueController extends BaseController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRescueAppService rescueAppService;

    @RequestMapping("/list")
    public ModelAndView list(ListRescueCommand command) {

        return new ModelAndView("/rescue/list", "command", command)
                .addObject("pagination", rescueAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateRescueCommand command) {
        return new ModelAndView("/rescue/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("command") CreateRescueCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {
        if(bindingResult.hasErrors()){
            return new ModelAndView("/rescue/create","command",command);
        }

        AlertMessage alertMessage=null;
        RescueRepresentation rescueRepresentation=null;
        try {
            rescueRepresentation=  rescueAppService.create(command);
        }catch (Exception e){
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/rescue/create","command",command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
        }
        logger.info("创建评价成功id=[" + rescueRepresentation.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", rescueRepresentation.getId());
        return new ModelAndView("redirect:/rescue/show/{id}");
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale){
        AlertMessage alertMessage;
        RescueRepresentation rescueRepresentation=null;
        try{
           rescueRepresentation= rescueAppService.show(id);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/rescue/list");
        }
        return new ModelAndView("/rescue/show","rescueRepresentation",rescueRepresentation);
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command")EditRescueCommand command ,
                             RedirectAttributes redirectAttributes,Locale locale){
        AlertMessage alertMessage;

        RescueRepresentation rescueRepresentation=null;

        try {
           rescueRepresentation= rescueAppService.show(command.getId());
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/rescue/list");

        }
        return new ModelAndView("/rescue/edit","command",command).addObject("rescueRepresentation",rescueRepresentation);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command")EditRescueCommand command,
                             BindingResult bindingResult,RedirectAttributes redirectAttributes,Locale locale){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/rescue/edit","command",command);
        }

        AlertMessage alertMessage;

        RescueRepresentation rescueRepresentation=null;
        try{
            rescueRepresentation= rescueAppService.edit(command);
        }catch (ConcurrencyException e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            redirectAttributes.addAttribute("id",command.getId());

            return new ModelAndView("redirect:/rescue/edit/{id}");

        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/rescue/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        logger.info("修改评价成功id=[" + rescueRepresentation.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", rescueRepresentation.getId());

        return new ModelAndView("redirect:/rescue/show/{id}");

    }

    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.GET)
    public ModelAndView updateStatus(@PathVariable String id,@ModelAttribute("command")EditRescueCommand command,
                                     RedirectAttributes redirectAttributes,Locale locale ){
        AlertMessage alertMessage;

        RescueRepresentation rescueRepresentation=null;

        try {
            rescueRepresentation= rescueAppService.updateStatus(command);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/rescue/list");

        }
        return new ModelAndView("/rescue/updateStatus","command",command).addObject("rescueRepresentation",rescueRepresentation);

    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    public ModelAndView updateStatus(@Valid @ModelAttribute("command")EditRescueCommand command,
                                     BindingResult bindingResult,RedirectAttributes redirectAttributes,Locale locale){

        if(bindingResult.hasErrors()){
            return new ModelAndView("/rescue/edit","command",command);
        }

        AlertMessage alertMessage;

        RescueRepresentation rescueRepresentation=null;
        try{
            rescueRepresentation= rescueAppService.updateStatus(command);
        }catch (ConcurrencyException e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            redirectAttributes.addAttribute("id",command.getId());

            return new ModelAndView("redirect:/rescue/updateStatus/{id}");

        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/rescue/updateStatus", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        logger.info("修改评价状态成功id=[" + rescueRepresentation.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.updateStatus.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", rescueRepresentation.getId());

        return new ModelAndView("redirect:/rescue/show/{id}");

    }
}
