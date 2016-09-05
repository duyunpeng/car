package pengyi.core.upload;

import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
public class FileUploadConfig {

    private String path;

    private String temp;

    private long maxSize;

    private List<String> type;

    private String folder;                      // 保存文件的目录 同时也是稳健访问路径目录(domainName/folder/xxxx.png)

    private String domainName;

    private int thumbnailWidth;

    private int thumbnailHeight;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        if (!folder.endsWith("/")) {
            folder += "/";
        }
        this.folder = folder;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        domainName = domainName.toLowerCase();
        if (!domainName.startsWith("http://")) {
            domainName = "http://" + domainName;
        }

        if (!domainName.endsWith("/")) {
            domainName += "/";
        }
        this.domainName = domainName;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        if (!temp.endsWith("/")) {
            temp += "/";
        }
        this.temp = temp;
    }

}
