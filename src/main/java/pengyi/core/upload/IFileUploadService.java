package pengyi.core.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IFileUploadService {

    FileUploadResult upload(MultipartFile[] files);

    FileUploadResult delete(String filename);

    FileUploadResult deleteTemp(String filename);

    FileUploadResult move(String filename);

}
