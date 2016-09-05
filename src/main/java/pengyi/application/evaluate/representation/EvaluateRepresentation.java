package pengyi.application.evaluate.representation;

import pengyi.application.car.representation.CarRepresentation;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class EvaluateRepresentation {

    private String id;
    private Integer version;

    private BaseUserRepresentation evaluateUser;               //评价人
    private OrderRepresentation order;                            //订单
    private String content;                                    //评价内容
    private int level;                                         //评级
    private String createDate;                                 //评价时间
    private CarRepresentation car;                              //车

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BaseUserRepresentation getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(BaseUserRepresentation evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public OrderRepresentation getOrder() {
        return order;
    }

    public void setOrder(OrderRepresentation order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarRepresentation getCar() {
        return car;
    }

    public void setCar(CarRepresentation car) {
        this.car = car;
    }
}
