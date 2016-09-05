package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EvaluateStatus;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.order.OrderService;
import pengyi.domain.service.user.BaseUserService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.*;

/**
 * Created by lvdi on 2016/3/8.
 */
@Service("evaluateService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class EvaluateService implements IEvaluateService {

    @Autowired
    private IEvaluateRepository<Evaluate, String> evaluateRepository;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IDriverService driverService;

    @Override
    public Pagination<Evaluate> pagination(ListEvaluateCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getOrder())) {
            criteriaList.add(Restrictions.like("order.orderNumber", command.getOrder(), MatchMode.ANYWHERE));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));
        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("order", "order");


        return evaluateRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, aliasMap, orderList, null, null);
    }

    /**
     * 评价人修改评价
     */
    @Override
    public void edit(EditEvaluateCommand command) {
        Evaluate evaluate = this.show(command.getId());
//        Evaluate evaluate1=this.searchByOrder(command.getOrder());
//        BaseUser baseUser = baseUserService.show(command.getEvaluateUser());
//        Order order = orderService.show(command.getOrder());
////        TODO  获取订单信息
////        Order order=OrderSer
        evaluate.fainWhenConcurrencyViolation(command.getVersion());
        evaluate.setContent(command.getContent());
        evaluate.setLevel(command.getLevel());
        evaluateRepository.update(evaluate);

    }

    /**
     * 根据id查看评价
     */
    @Override
    public Evaluate show(String id) {

        Evaluate evaluate = (Evaluate) evaluateRepository.getById(id);
        if (null == evaluate) {
            throw new NoFoundException("没有找到评价id=[" + id + "]的记录");
        }
        return evaluate;
    }


    /**
     * 通过订单查看评价
     */
    @Override
    public List<Evaluate> searchByOrder(String order) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("order.id", order));
        return evaluateRepository.list(criterionList, null);
    }


    /**
     * 发起评价
     */
    @Override
    public void create(CreateEvaluateCommand command) {

        Order order = orderService.show(command.getOrderId());
        BaseUser baseUser = baseUserService.searchByUserName(command.getEvaluateUser());
        Evaluate evaluate = new Evaluate(baseUser, order, command.getContent(), command.getLevel(), new Date());
        evaluateRepository.save(evaluate);
    }

    @Override
    public Double reckonDriverLevel(String driverId) {
        return evaluateRepository.reckonDriverLevel(driverId);
    }

    @Override
    public Evaluate apiCreateEvaluate(CreateEvaluateCommand command) {
        Order order = orderService.show(command.getOrderId());
        BaseUser baseUser = baseUserService.show(command.getEvaluateUser());
        if(baseUser.getId() != order.getReceiveUser().getId() && baseUser.getId() != order.getOrderUser().getId()){
            throw new ExistException("您不能评价此订单");
        }
        if (order.getEvaluateStatus() == EvaluateStatus.OK_EVALUATE) {
            throw new ExistException("该订单已完成评价");
        }
        if (baseUser.getUserType() == order.getOrderUser().getUserType()) {
            if (order.getEvaluateStatus() != EvaluateStatus.USER_EVALUATE && order.getEvaluateStatus() != EvaluateStatus.OK_EVALUATE) {
                Evaluate evaluate = new Evaluate(baseUser, order, command.getContent(), command.getLevel(), new Date());
                evaluateRepository.save(evaluate);
                driverService.updateDriverLevel(order.getReceiveUser().getId(), reckonDriverLevel(order.getReceiveUser().getId()));
                if (order.getEvaluateStatus() == EvaluateStatus.NOT_EVALUATE) {
                    orderService.updateEvaluate(order.getId(), EvaluateStatus.USER_EVALUATE);
                } else {
                    orderService.updateEvaluate(order.getId(), EvaluateStatus.OK_EVALUATE);
                }
                return evaluate;
            } else {
                throw new ExistException("您已经评价该订单");
            }
        } else if (baseUser.getUserType() == order.getReceiveUser().getUserType()) {
            if (order.getEvaluateStatus() != EvaluateStatus.DRIVER_EVALUATE && order.getEvaluateStatus() != EvaluateStatus.OK_EVALUATE) {
                Evaluate evaluate = new Evaluate(baseUser, order, command.getContent(), command.getLevel(), new Date());
                evaluateRepository.save(evaluate);
                if (order.getEvaluateStatus() == EvaluateStatus.NOT_EVALUATE) {
                    orderService.updateEvaluate(order.getId(), EvaluateStatus.DRIVER_EVALUATE);
                } else {
                    orderService.updateEvaluate(order.getId(), EvaluateStatus.OK_EVALUATE);
                }
                return evaluate;
            } else {
                throw new ExistException("您已经评价该订单");
            }
        } else {
            throw new ExistException("您与这笔订单没有关系");
        }
    }

    /**
     * 删除评价
     */
    @Override
    public void delete(String orderId) {
        Order order = orderService.show(orderId);
        Evaluate evaluate = this.show(orderId);
        evaluateRepository.delete(evaluate);
        driverService.updateDriverLevel(order.getReceiveUser().getId(), reckonDriverLevel(order.getReceiveUser().getId()));
        orderService.updateEvaluate(order.getId(), EvaluateStatus.NOT_EVALUATE);
    }

}
