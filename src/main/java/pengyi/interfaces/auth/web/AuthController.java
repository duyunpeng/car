package pengyi.interfaces.auth.web;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.user.IBaseUserAppService;
import pengyi.application.user.command.LoginUserCommand;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/8.
 */
@Controller
public class AuthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ImageCaptchaService imageCaptchaService;

    @Autowired
    private IBaseUserAppService baseUserAppService;

    @RequestMapping(value = "/verificationCode", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    @ResponseBody
    public byte[] verificationCode(HttpServletRequest request) throws Exception {
        String captchaId = request.getRequestedSessionId();
        BufferedImage bufferedImage = (BufferedImage) imageCaptchaService.getChallengeForID(captchaId, request.getLocale());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("user") LoginUserCommand command, RedirectAttributes redirectAttributes,
                              HttpServletRequest request, HttpSession session, BindingResult bindingResult, Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/login", "user", command);
        }

        AlertMessage alertMessage;

        if (command.getVerificationCode().isEmpty()) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.NotEmpty.message", null, locale));
            return new ModelAndView("/login", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage)
                    .addObject("user", command);
        }
        boolean flag = false;
        try {
            flag = imageCaptchaService.validateResponseForID(request.getRequestedSessionId(),
                    command.getVerificationCode());
        } catch (CaptchaServiceException e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.Error.message", null, locale));
            return new ModelAndView("/login", "user", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }


        if (flag) {

            try {
                BaseUser user = baseUserAppService.login(command);
                Subject subject = SecurityUtils.getSubject();
                if (subject.hasRole("terrace")) {
                    logger.info("管理员:" + subject.getPrincipal() + "登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/logined");
                } else {
                    subject.logout();
                    alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                            this.getMessage("login.NoPermission.Error.message", null, locale));
                    redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                    return new ModelAndView("redirect:/");
                }
            } catch (UnknownAccountException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.account.NotExists.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (IncorrectCredentialsException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.account.Error.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (LockedAccountException e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.Disable.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            } catch (Exception e) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.login.Failure.message", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/");
            }
        } else {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.verificationCode.Error.message", null, locale));
            return new ModelAndView("/login", "user", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if (null == subject.getPrincipal()) {
            return new ModelAndView("redirect:/");
        } else {
            try {
                String username = subject.getPrincipal().toString();
                logger.info("用户:" + username + "登出成功！时间:" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return new ModelAndView("redirect:/");
            }
        }
    }

    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return new ModelAndView("/unauthorized");
    }


}
