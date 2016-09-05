package pengyi.domain.service.api;

import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pengyi on 2016/3/17.
 */
public interface IApiService {

    void command(String id, HttpServletRequest request) throws DocumentException, SAXException, IOException;

}
