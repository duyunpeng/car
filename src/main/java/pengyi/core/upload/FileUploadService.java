package pengyi.core.upload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import pengyi.core.util.CoreStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
public class FileUploadService implements IFileUploadService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String DOT = ".";

    private FileUploadConfig fileUploadConfig;

    @Override
    public FileUploadResult upload(MultipartFile[] files) {

        List<Object> resultFiles = new ArrayList<Object>();
        File folder = new File(fileUploadConfig.getPath(), fileUploadConfig.getTemp());
        folder.mkdirs();

        int width = fileUploadConfig.getThumbnailWidth();
        int height = fileUploadConfig.getThumbnailHeight();
        String baseUrl = fileUploadConfig.getDomainName() + fileUploadConfig.getTemp();

        for (MultipartFile file : files) {
            String message = null;
            if (file.isEmpty()) {
                message = "文件未上传";
                log.info("文件未上传");
            }

            String filename = file.getOriginalFilename();
            String type = (filename.substring(filename.lastIndexOf(DOT) + 1)).toLowerCase();
            long fileSize = file.getSize();

            if (fileUploadConfig.getMaxSize() < fileSize) {
                message = "文件过大，无法上传！";
                log.info("上传文件[{}]大小[{}]超过了[{}]", filename, file, fileUploadConfig.getMaxSize());
            }

            if (!fileUploadConfig.getType().contains(type)) {
                message = "文件类型错误！";
                log.info("上传文件[{}]类型[{}]错误", filename, type);
            }

            if (CoreStringUtils.isEmpty(message)) {
                String saveFilename = String.valueOf(System.currentTimeMillis());

                File saveFile = new File(folder, CoreStringUtils.join(DOT, saveFilename, type));
                File thumbnailFile = new File(folder, CoreStringUtils.join(DOT, width, height,
                        saveFilename, type));

                try {
                    file.transferTo(saveFile);
                    log.info("上传文件[{}]成功", saveFile.getAbsolutePath());
                } catch (IOException e) {
                    resultFiles.add(new FileUploadFailure(file.getOriginalFilename(), file.getSize(), message));
                    log.error("上传文件失败.", e);
                }

                try {
                    Thumbnails.of(saveFile)
                            .size(width, height)
                            .keepAspectRatio(true)                 // 是否按比例缩放
                            .toFile(thumbnailFile);
                    log.info("生成缩略图文件[{}]成功", thumbnailFile.getAbsolutePath());
                } catch (IOException e) {
                    resultFiles.add(new FileUploadFailure(file.getOriginalFilename(), file.getSize(), message));
                    log.error("对文件[{}]生成缩略图失败.", saveFile, e);
                    deleteFile(saveFile);
                }

                resultFiles.add(new FileUploadSuccess(saveFile.getName(), fileSize,
                        baseUrl + saveFile.getName(),
                        baseUrl + thumbnailFile.getName(),
                        CoreStringUtils.join(null, "/uploadFile/deleteTemp?fileName=",
                                saveFile.getName()),
                        "DELETE"));
            } else {
                resultFiles.add(new FileUploadFailure(file.getOriginalFilename(), file.getSize(), message));
            }
        }

        return new FileUploadResult(resultFiles.toArray());
    }

    @Override
    public FileUploadResult delete(String filename) {
        JSONArray jsonArray = new JSONArray();
        File folder = new File(fileUploadConfig.getPath(), fileUploadConfig.getFolder());

        File file = new File(folder, filename);
        File thumbnailFile = new File(folder, CoreStringUtils.join(DOT, fileUploadConfig.getThumbnailWidth(),
                fileUploadConfig.getThumbnailHeight(), filename));

        if (deleteFile(file) && deleteFile(thumbnailFile)) {
            jsonArray.add(new JSONObject().put(filename, Boolean.TRUE));
        }

        return new FileUploadResult(jsonArray.toArray());
    }

    @Override
    public FileUploadResult deleteTemp(String filename) {
        JSONArray jsonArray = new JSONArray();
        File folder = new File(fileUploadConfig.getPath(), fileUploadConfig.getTemp());

        File file = new File(folder, filename);
        File thumbnailFile = new File(folder, CoreStringUtils.join(DOT, fileUploadConfig.getThumbnailWidth(),
                fileUploadConfig.getThumbnailHeight(), filename));

        if (deleteFile(file) && deleteFile(thumbnailFile)) {
            jsonArray.add(new JSONObject().put(filename, Boolean.TRUE));
        }

        return new FileUploadResult(jsonArray.toArray());
    }

    @Override
    public FileUploadResult move(String filename) {

        JSONArray jsonArray = new JSONArray();

        File temp = new File(fileUploadConfig.getPath(), fileUploadConfig.getTemp());
        File tempFile = new File(temp, filename);
        File thumbnailTempFile = new File(temp, CoreStringUtils.join(DOT, fileUploadConfig.getThumbnailWidth(),
                fileUploadConfig.getThumbnailHeight(), filename));

        File path = new File(fileUploadConfig.getPath(), fileUploadConfig.getFolder());
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, filename);
        File thumbnailFile = new File(path, CoreStringUtils.join(DOT, fileUploadConfig.getThumbnailWidth(),
                fileUploadConfig.getThumbnailHeight(), filename));

        try {
            FileUtils.moveFile(tempFile, file);
            FileUtils.moveFile(thumbnailTempFile, thumbnailFile);
            jsonArray.add(new JSONObject().put(filename, Boolean.TRUE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileUploadResult(jsonArray.toArray());
    }

    private boolean deleteFile(File file) {
        boolean flag = FileUtils.deleteQuietly(file);
        if (flag) {
            log.info("删除文件[{}]成功", file.getAbsolutePath());
        } else {
            log.error("删除文件[{}]失败", file.getAbsolutePath());
        }
        return flag;
    }

    public void setFileUploadConfig(FileUploadConfig fileUploadConfig) {
        this.fileUploadConfig = fileUploadConfig;
    }

}
