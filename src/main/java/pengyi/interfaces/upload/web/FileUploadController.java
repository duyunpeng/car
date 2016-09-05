package pengyi.interfaces.upload.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pengyi.core.upload.FileUploadResult;
import pengyi.core.upload.IFileUploadService;

/**
 * Created by YJH on 2016/3/9.
 */
@Controller
@RequestMapping(value = "/uploadFile")
public class FileUploadController {

    @Autowired
    private IFileUploadService uploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadResult upload(@RequestParam MultipartFile[] files) {
        return uploadService.upload(files);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public FileUploadResult delete(String fileName) {
        return uploadService.delete(fileName);
    }

    @RequestMapping(value = "/deleteTemp")
    @ResponseBody
    public FileUploadResult deleteTemp(String fileName) {
        return uploadService.deleteTemp(fileName);
    }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadResult uploadImg(@RequestParam MultipartFile[] file) {
        return uploadService.upload(file);
    }
}
