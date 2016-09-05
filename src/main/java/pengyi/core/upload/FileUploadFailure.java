package pengyi.core.upload;

/**
 * Created by YJH on 2016/3/8.
 */
public class FileUploadFailure {

    private String name;
    private long size;
    private String error;

    public FileUploadFailure(String name, long size, String error) {
        this.name = name;
        this.size = size;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
