package pengyi.domain.service.withdraw;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.CreateMessageByBaseUserCommand;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.core.type.FlowType;
import pengyi.core.type.MessageType;
import pengyi.core.type.WithdrawStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.withdraw.IWithdrawRepository;
import pengyi.domain.model.withdraw.Withdraw;
import pengyi.domain.service.message.IMessageService;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.util.*;

/**
 * Created by pengyi on 2016/5/6.
 */
@Service("withdrawService")
public class WithdrawService implements IWithdrawService {

    @Autowired
    private IBaseUserService baseUserService;
    @Autowired
    private IWithdrawRepository<Withdraw, String> withdrawRepository;
    @Autowired
    private IMoneyDetailedService moneyDetailedService;
    @Autowired
    private IMessageService messageService;

    @Override
    public void apply(CreateWithdrawCommand command) throws NotSufficientFundsException {

        BaseUser user = baseUserService.show(command.getUserId());

        if (user.getBalance().doubleValue() < command.getMoney().doubleValue()) {
            throw new NotSufficientFundsException();
        }

        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();

        Withdraw withdraw = new Withdraw(user, new Date(), command.getMoney(), WithdrawStatus.PENDING, null);
        withdrawRepository.save(withdraw);
        moneyDetailedCommand.setOldMoney(user.getBalance());

        baseUserService.subtractBalance(command.getUserId(), command.getMoney());

        moneyDetailedCommand.setBaseUser(command.getUserId());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setMoney(command.getMoney());
        moneyDetailedCommand.setExplain("提现:" + withdraw.getId());
        moneyDetailedCommand.setNewMoney(user.getBalance());
        moneyDetailedService.create(moneyDetailedCommand);

    }

    @Override
    public Pagination<Withdraw> list(ListWithdrawCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("baseUser.id", command.getBaseUser()));

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createTime"));

        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("baseUser", "baseUser");
        return withdrawRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public Pagination<Withdraw> pagination(ListWithdrawCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getBaseUser())) {
            criterionList.add(Restrictions.like("baseUser.userName", command.getBaseUser(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createTime", CoreDateUtils.parseDate(command.getStartTime(),"yyyy/MM/dd HH:mm"), CoreDateUtils.parseDate(command.getEndTime(),"yyyy/MM/dd HH:mm")));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createTime"));

        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("baseUser", "baseUser");
        return withdrawRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public void finish(EditWithdrawCommand command) {
        Withdraw withdraw = withdrawRepository.getById(command.getId());
        withdraw.setStatus(WithdrawStatus.FINISH);
        withdrawRepository.save(withdraw);

        CreateMessageByBaseUserCommand messageByBaseUserCommand = new CreateMessageByBaseUserCommand();
        messageByBaseUserCommand.setSendBaseUser(command.getLoginUser());
        messageByBaseUserCommand.setReceiveBaseUser(withdraw.getBaseUser().getId());
        messageByBaseUserCommand.setContent("您申请的提现" + withdraw.getId() + "已经处理，金额:" + withdraw.getMoney());
        messageByBaseUserCommand.setType(MessageType.SYSTEM_MESSAGE);
        messageService.createByBaseUser(messageByBaseUserCommand);
    }

    @Override
    public List<Withdraw> exportExcel(ListWithdrawCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getBaseUser())) {
            criterionList.add(Restrictions.like("baseUser.userName", command.getBaseUser(), MatchMode.ANYWHERE));
            alias.put("baseUser", "baseUser");
        }

        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }

        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createTime", CoreDateUtils.parseDateStart(command.getStartTime()), CoreDateUtils.parseDateEnd(command.getEndTime())));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createTime"));
        return withdrawRepository.list(criterionList, orderList, null, null, alias);
    }
}
