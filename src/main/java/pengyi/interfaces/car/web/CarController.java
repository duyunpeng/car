package pengyi.interfaces.car.web;

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
import pengyi.application.car.ICarAppService;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.application.car.representation.CarRepresentation;
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
@RequestMapping("/car")
public class CarController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICarAppService carAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListCarCommand command){
        return new ModelAndView("/car/list","command",command).addObject("pagination",carAppService.pagination(command));
    }

    /**
     * 创建车辆
     */
    @RequestMapping(value ="/create",method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateCarCommand command){
        return new ModelAndView("/car/create","command",command);
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateCarCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/car/create");
        }
        AlertMessage alertMessage = null;

        CarRepresentation carRepresentation = null;
        try {

            carRepresentation = carAppService.create(command);
        } catch (Exception e) {

            logger.error(e.getMessage());

            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/car/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("创建车辆成功driver=[" + carRepresentation.getDriver() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));

        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", carRepresentation.getDriver());

        return new ModelAndView("redirect:/car/show/"+carRepresentation.getId());


    }

    /**
     * 根据id车看车辆
     */
    @RequestMapping(value = "show/{id}",method =RequestMethod.GET)
    public ModelAndView show(@PathVariable String id,  RedirectAttributes redirectAttributes, Locale locale){
        AlertMessage alertMessage;
        CarRepresentation carRepresentation=null;

        try {
            carRepresentation=carAppService.show(id);

        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/car/list");

        }
        return new ModelAndView("/car/show","car",carRepresentation);
    }

    /**
     *根据id修改车辆
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id,@ModelAttribute("command")EditCarCommand command,
                             RedirectAttributes redirectAttributes, Locale locale){
        AlertMessage alertMessage;
        CarRepresentation carRepresentation=null;
        try {
                carRepresentation=carAppService.show(id);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/car/list");
        }
        return new ModelAndView("/car/edit","command",command).addObject("car",carRepresentation);
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditCarCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/car/edit", "command", command);
        }

        AlertMessage alertMessage;
        CarRepresentation permission = null;

        try {
            permission = carAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/car/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/car/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改car成功id=[" + permission.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", permission.getId());

        return new ModelAndView("redirect:/car/show/{id}");
    }


}



