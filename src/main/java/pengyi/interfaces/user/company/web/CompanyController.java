package pengyi.interfaces.user.company.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.application.user.company.ICompanyAppService;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.repository.generic.Pagination;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping("/user/company")
public class CompanyController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICompanyAppService companyAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(BaseListCompanyCommand command) {
        return new ModelAndView("/baseuser/company/list", "command", command)
                .addObject("pagination", companyAppService.pagination(command));
    }

    @RequestMapping(value = "/auth_list")
    public ModelAndView auth_list(BaseListCompanyCommand command) {
        command.setStatus(EnableStatus.DISABLE);
        return new ModelAndView("/baseuser/company/authlist", "command", command)
                .addObject("pagination", companyAppService.pagination(command));
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        CompanyRepresentation company = null;
        try {
            company = companyAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/company/list");
        }
        return new ModelAndView("/baseuser/company/show", "company", company);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditCompanyCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        CompanyRepresentation company = null;

        try {
            company = companyAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/company/list");
        }

        return new ModelAndView("/baseuser/company/edit", "command", command).addObject("company", company);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditCompanyCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/company/edit", "command", command);
        }

        AlertMessage alertMessage;
        CompanyRepresentation company = null;

        try {
            company = companyAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/user/company/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/baseuser/company/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改company(公司)用户成功id=[" + company.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", company.getId());

        return new ModelAndView("redirect:/user/company/show/{id}");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(EditStatusCommand command,
                                     RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        CompanyRepresentation company = null;
        try {
            company = companyAppService.updateStatus(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("redirect:/user/company/auth_list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改用户状态成功id=[" + company.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/user/company/auth_list");
    }

    @RequestMapping(value = "/company_list")
    @ResponseBody
    public Pagination<CompanyRepresentation> permissionList(@RequestBody BaseListCompanyCommand command) {
        return companyAppService.paginationList(command);
    }

    @RequestMapping(value = "/all_list")
    @ResponseBody
    public List<CompanyRepresentation> jsonList() {
        return companyAppService.allList();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(CreateCompanyCommand command) {
        return new ModelAndView("/baseuser/company/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateCompanyCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/baseuser/company/create");
        }

        AlertMessage alertMessage;
        CompanyRepresentation company;
        try {
            company = companyAppService.create(command);
        } catch (ExistException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("user.userName.exist.message", new Object[]{command.getUserName()}, locale));
            return new ModelAndView("/baseuser/company/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoFoundException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.create.failure.message", null, locale));
            return new ModelAndView("/baseuser/company/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("default.create.failure.message", null, locale));
            return new ModelAndView("/baseuser/company/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", company.getId());
        return new ModelAndView("redirect:/user/company/show/{id}");
    }
}
