package pengyi.interfaces.feedback.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.feedback.IFeedBackAppService;
import pengyi.application.feedback.command.ListFeedBackCommand;
import pengyi.application.feedback.representation.FeedBackRepresentation;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import java.util.Locale;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping(value = "/feed_back")
public class FeedBackController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IFeedBackAppService feedBackAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListFeedBackCommand command) {
        return new ModelAndView("/feedback/list", "pagination", feedBackAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        FeedBackRepresentation feedBack = null;

        try {
            feedBack = feedBackAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/feed_back/list");
        }
        return new ModelAndView("/feedback/show", "feedBack", feedBack);
    }

}
