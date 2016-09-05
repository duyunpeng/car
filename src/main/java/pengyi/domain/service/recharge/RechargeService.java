package pengyi.domain.service.recharge;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.application.recharge.command.ListRechargeCommand;
import pengyi.core.commons.Constants;
import pengyi.core.exception.WechatSignException;
import pengyi.core.pay.wechat.UnifiedRequest;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.core.type.FlowType;
import pengyi.core.util.*;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.model.recharge.IRechargeRepository;
import pengyi.domain.model.recharge.Recharge;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by pengyi on 2016/4/11.
 */
@Service("rechargeService")
public class RechargeService implements IRechargeService {

    @Autowired
    private IRechargeRepository<Recharge, String> rechargeRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Override
    public UnifiedResponse wechatPay(CreateRechargeCommand command) {

        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            Recharge recharge = new Recharge(baseUser, command.getMoney(), command.getPayType());
            rechargeRepository.save(recharge);

            String body = "余额充值：" + recharge.getId();
            String detail = command.getUserName() + "充值￥" + command.getMoney();
            UnifiedRequest request = new UnifiedRequest(command.getUserType(), body, detail, recharge.getId(), command.getMoney()
                    .multiply(new BigDecimal(100)).intValue(), command.getIp(), Constants.WECHAT_RECHARGE_NOTIFY_URL);

            try {
                String sign = Signature.getSign(request.toMap());
                request.setSign(sign);
                XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
                String s = HttpUtil.urlConnection(Constants.WECHAT_UNIFIED_URL, xStream.toXML(request));

                UnifiedResponse response = null;
                response = (UnifiedResponse) XMLParser.getObjFromXML(s, UnifiedResponse.class);
                if (response != null) {
                    response.setTime_stamp(System.currentTimeMillis() / 1000);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("appid", response.getAppid());
                    map.put("partnerid", response.getMch_id());
                    map.put("prepayid", response.getPrepay_id());
                    map.put("package", "Sign=WXPay");
                    map.put("noncestr", response.getNonce_str());
                    map.put("timestamp", response.getTime_stamp());
                    response.setSign(Signature.getSign(map));

                }
                return response;
            } catch (ParserConfigurationException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (IOException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (SAXException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (Exception e) {
                throw new WechatSignException("未知错误");
            }
        }

        return null;
    }

    @Override
    public Recharge alipayPay(CreateRechargeCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            Recharge recharge = new Recharge(baseUser, command.getMoney(), command.getPayType());
            rechargeRepository.save(recharge);
            return recharge;
        }
        return null;
    }

    @Override
    public void alipaySuccess(AlipayNotify notify) {

        Recharge recharge = rechargeRepository.getById(notify.getOut_trade_no());

        if (null != recharge && !recharge.isPayed() && recharge.getMoney().toString().equals(notify.getTotal_fee())) {
            recharge.setPayTime(CoreDateUtils.parseLongDate(notify.getGmt_payment()));
            recharge.setPayed(true);
            recharge.setPayNo(notify.getTrade_no());

            CreateMoneyDetailedCommand command = new CreateMoneyDetailedCommand();
            command.setBaseUser(recharge.getUser().getId());
            command.setCreateDate(new Date());
            command.setExplain(notify.getSubject());
            command.setFlowType(FlowType.IN_FLOW);
            command.setMoney(recharge.getMoney());
            command.setNewMoney(recharge.getUser().getBalance().add(recharge.getMoney()));
            command.setOldMoney(recharge.getUser().getBalance());
            moneyDetailedService.create(command);

            baseUserService.addBalance(recharge.getUser().getId(), recharge.getMoney());

            rechargeRepository.update(recharge);
        }

    }

    @Override
    public void wechatSuccess(WechatNotify notify) {

        Recharge recharge = rechargeRepository.getById(notify.getOut_trade_no());

        if (null != recharge && !recharge.isPayed() && recharge.getMoney().multiply(new BigDecimal(100)).intValue() == notify.getTotal_fee()) {
            recharge.setPayTime(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            recharge.setPayed(true);
            recharge.setPayNo(notify.getTransaction_id());

            CreateMoneyDetailedCommand command = new CreateMoneyDetailedCommand();
            command.setBaseUser(recharge.getUser().getId());
            command.setCreateDate(new Date());
            command.setExplain("余额充值：" + recharge.getId());
            command.setFlowType(FlowType.IN_FLOW);
            command.setMoney(recharge.getMoney());
            command.setNewMoney(recharge.getUser().getBalance().add(recharge.getMoney()));
            command.setOldMoney(recharge.getUser().getBalance());
            moneyDetailedService.create(command);

            baseUserService.addBalance(recharge.getUser().getId(), recharge.getMoney());
        }

        rechargeRepository.update(recharge);
    }

    @Override
    public Pagination<Recharge> pagination(ListRechargeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("user.userName", command.getUserName(), MatchMode.ANYWHERE));
            alias.put("user", "user");
        }
        if (!CoreStringUtils.isEmpty(command.getStartCreateDate()) && null != CoreDateUtils.parseDate(command.getStartCreateDate(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createTime", CoreDateUtils.parseDate(command.getStartCreateDate(),"yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndCreateDate()) && null != CoreDateUtils.parseDate(command.getEndCreateDate(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createTime", CoreDateUtils.parseDate(command.getEndCreateDate(),"yyyy/MM/dd HH:mm")));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createTime"));
        return rechargeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, alias, orderList, null, null);
    }

}