package pengyi.interfaces.api;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import pengyi.domain.service.api.IApiService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IApiService apiService;

    @RequestMapping(value = "/api")
    public ModelAndView api() {
        try {
            return new ModelAndView("/api", "doc", freemarker.ext.dom.NodeModel.parse(new File(getClass().getResource("/").getPath() + "api.xml")));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return new ModelAndView("/api");
    }

    @RequestMapping(value = "/apicommand/{id}")
    @ResponseBody
    public String apicommand(@PathVariable String id, HttpServletRequest request) {
        try {
            apiService.command(id, request);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }


}
