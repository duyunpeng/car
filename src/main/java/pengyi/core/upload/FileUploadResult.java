package pengyi.core.upload;

/**
 * Created by YJH on 2016/3/8.
 */
public class FileUploadResult {

    private Object[] files;

    public FileUploadResult(Object[] files) {
        this.files = files;
    }

    public Object[] getFiles() {
        return files;
    }

    public void setFiles(Object[] files) {
        this.files = files;
    }

}
