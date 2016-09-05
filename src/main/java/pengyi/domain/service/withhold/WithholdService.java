package pengyi.domain.service.withhold;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.CreateMessageByBaseUserCommand;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.core.type.FlowType;
import pengyi.core.type.MessageType;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.withhold.IWithholdRepository;
import pengyi.domain.model.withhold.Withhold;
import pengyi.domain.service.message.IMessageService;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.util.*;

/**
 * Created by pengyi on 2016/5/6.
 */
@Service("withholdService")
public class WithholdService implements IWithHoldService {

    @Autowired
    private IWithholdRepository<Withhold, String> withholdRepository;
    @Autowired
    private IBaseUserService baseUserService;
    @Autowired
    private IMoneyDetailedService moneyDetailedService;
    @Autowired
    private IMessageService messageService;

    @Override
    public void create(CreateWithholdCommand command) {

        BaseUser baseUser = baseUserService.show(command.getUserId());
        Withhold withhold = new Withhold(baseUser, new Date(), command.getMoney(), command.getDetail());
        withholdRepository.save(withhold);
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();
        moneyDetailedCommand.setOldMoney(baseUser.getBalance());

        baseUserService.subtractBalance(command.getUserId(), command.getMoney());

        moneyDetailedCommand.setBaseUser(command.getUserId());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setMoney(command.getMoney());
        moneyDetailedCommand.setExplain("扣款:" + withhold.getId());
        moneyDetailedCommand.setNewMoney(baseUser.getBalance());
        moneyDetailedService.create(moneyDetailedCommand);

        CreateMessageByBaseUserCommand messageByBaseUserCommand = new CreateMessageByBaseUserCommand();
        messageByBaseUserCommand.setSendBaseUser(command.getLoginUser());
        messageByBaseUserCommand.setReceiveBaseUser(command.getUserId());
        messageByBaseUserCommand.setContent("扣款：" + withhold.getId() + "，扣款原因：" + command.getDetail());
        messageByBaseUserCommand.setType(MessageType.SYSTEM_MESSAGE);
        messageService.createByBaseUser(messageByBaseUserCommand);

    }

    @Override
    public Pagination<Withhold> pagination(ListWithholdCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getLoginUser())) {
            criterionList.add(Restrictions.eq("company.id", command.getLoginUser()));
        }
        if (!CoreStringUtils.isEmpty(command.getBaseUser())) {
            criterionList.add(Restrictions.like("baseUser.userName", command.getBaseUser(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createTime", CoreDateUtils.parseDate(command.getStartTime(), "yyyy/MM/dd HH:mm"), CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createTime"));
        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("baseUser", "baseUser");
        return withholdRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public Withhold show(String id) {
        return withholdRepository.getById(id);
    }
}
