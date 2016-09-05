package pengyi.application.rescue.command;

/**
 * Created by pengyi on 2016/5/6.
 */
public class RescueSuccessCommand {

    private String id;
    private Integer version;

    private String images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
