package pengyi.application.order.command;

/**
 * Created by YJH on 2016/5/10.
 */
public class DriverCreateOrderCommand {

    private String driverId;
    private String userName;
    private String startAddress;                        //开始地址
    private String endAddress;                          //结束地址
    private String contactPhone;                        //为别人叫代驾被联系人电话号码

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
