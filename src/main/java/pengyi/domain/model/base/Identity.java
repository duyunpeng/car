package pengyi.domain.model.base;

import pengyi.core.exception.ConcurrencyException;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by pengyi on 2016/1/13.
 */
public abstract class Identity implements Serializable {
    private String id;
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Identity() {
        this.version = 0;
    }

    public void fainWhenConcurrencyViolation(Integer version){
        if(!version.equals(this.getVersion())){
            throw new ConcurrencyException("记录在提交之前已发生改变[id="+this.getId()+"],请重新提交.");
        }
    }
}
