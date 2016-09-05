package pengyi.domain.service.moneydetailed;

import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.EditMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.FlowType;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.moneydetailed.IMoneyDetailedRepository;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("moneyDetailedService")
public class MoneyDetailedService implements IMoneyDetailedService {

    @Autowired
    private IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Override
    public Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDate(command.getStartTime(),"yyyy/MM/dd HH:mm"), CoreDateUtils.parseDate(command.getEndTime(),"yyyy/MM/dd HH:mm")));
        }

        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("baseUser.userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (!CoreStringUtils.isEmpty(command.getUserId())) {
            criterionList.add(Restrictions.eq("baseUser.id", command.getUserId()));
        }

        if (null != command.getFlowType()) {
            criterionList.add(Restrictions.eq("flowType", command.getFlowType()));
        }

        if (!CoreStringUtils.isEmpty(command.getDate())) {
            String[] sp = command.getDate().split("-");
            if (sp.length == 2) {
                Calendar start = Calendar.getInstance();
                start.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, 1);
//                start.set(Calendar.DAY_OF_MONTH, start.getActualMaximum(Calendar.DAY_OF_MONTH));
                start.set(Calendar.HOUR_OF_DAY, 0);
                start.set(Calendar.MINUTE, 0);
                start.set(Calendar.SECOND, 0);

                Calendar end = Calendar.getInstance();
                end.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, 1);
                end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
                end.set(Calendar.HOUR_OF_DAY, 23);
                end.set(Calendar.MINUTE, 59);
                end.set(Calendar.SECOND, 59);
                criterionList.add(Restrictions.between("createDate", start.getTime(), end.getTime()));
            } else {
                criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getDate()), CoreDateUtils.parseDateEnd(command.getDate())));
            }
        }

        List<Order> orders = new ArrayList<Order>();
        orders.add(Order.desc("createDate"));

        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("baseUser", "baseUser");
        return moneyDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orders, null, null);
    }

    @Override
    public Number sum(ListMoneyDetailedCommand command) {
        if (null == command.getFlowType()) {
            command.setFlowType(FlowType.IN_FLOW);
            BigDecimal in = (BigDecimal) moneyDetailedRepository.sum(command);
            command.setFlowType(FlowType.OUT_FLOW);
            BigDecimal out = (BigDecimal) moneyDetailedRepository.sum(command);
            if (null == in) {
                in = new BigDecimal(0);
            }
            return (in.subtract(out));
        } else {
            return moneyDetailedRepository.sum(command);
        }
    }

    @Override
    public MoneyDetailed create(CreateMoneyDetailedCommand command) {
        BaseUser user = baseUserService.show(command.getBaseUser());

        MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(),
                command.getExplain(), command.getOldMoney(), command.getNewMoney(), new Date());

        moneyDetailedRepository.save(moneyDetailed);

        return moneyDetailed;
    }

    @Override
    public MoneyDetailed edit(EditMoneyDetailedCommand command) {
        MoneyDetailed moneyDetailed = this.show(command.getId());
        moneyDetailed.fainWhenConcurrencyViolation(command.getVersion());

        //TODO 修改内容 在定

        return moneyDetailed;
    }

    @Override
    public MoneyDetailed show(String id) {
        MoneyDetailed moneyDetailed = moneyDetailedRepository.getById(id);
        if (null == moneyDetailed) {
            throw new NoFoundException("没有找到资金流向id=[" + id + "]的记录");
        }
        return moneyDetailed;
    }

    @Override
    public List<MoneyDetailed> apiexportExcel(ListMoneyDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();
        if(!CoreStringUtils.isEmpty(command.getStartTime()) && !CoreStringUtils.isEmpty(command.getEndTime())){
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getStartTime()), CoreDateUtils.parseDateEnd(command.getEndTime())));
    }
        if(!CoreStringUtils.isEmpty(command.getUserName())){
            criterionList.add(Restrictions.like("baseUser.userName",command.getUserName(), MatchMode.ANYWHERE));
            alias.put("baseUser","baseUser");
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return moneyDetailedRepository.list(criterionList,orderList,null,null,alias);
    }
}
