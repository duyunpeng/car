package pengyi.application.report.command;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by liubowen on 2016/3/10.
 * 页面修改
 */
public class EditReportCommand {
    private String id;
    private Integer version;

    @NotEmpty(message = "{report.status,NotEmpty,message}")
    private String status;                  //状态（待处理、处理中、处理完成）

    @NotEmpty(message = "{report.handleResult,NotEmpty,message}")
    private String handleResult;                   //处理结果

    @NotEmpty(message = "{report.description,NotEmpty,message}")
    private String description;                 //说明

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
