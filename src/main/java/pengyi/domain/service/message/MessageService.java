package pengyi.domain.service.message;

import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.*;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.MessageType;
import pengyi.core.type.ShowType;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.repository.generic.Pagination;

import java.util.*;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository<Message, String> messageRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICompanyService companyService;

    @Override
    public Message getById(String messageId) {

        Message message = messageRepository.getById(messageId);
        if (null == message) {
            throw new NoFoundException("没有找到messageId=[" + messageId + "]的记录");
        }
        return message;
    }

    @Override
    public Message show(String messageId) {

        Message message = messageRepository.getById(messageId);
        if (null == message) {
            throw new NoFoundException("没有找到messageId=[" + messageId + "]的记录");
        }
        message.setReceiveDate(new Date());
        messageRepository.update(message);
        return message;
    }

    @Override
    //发送给角色
    /*添加的时候将发送时间设置为当前时间，接收时间为Null*/
    public void create(CreateMessageByRoleCommand command) {
        //从service中获取用户
        BaseUser sendUser = baseUserService.show(command.getSendBaseUser());

        //获取角色
        Role role = roleService.show(command.getUserRole());

        List<BaseUser> baseUsers = baseUserService.searchByUserRole(role.getId());

        for (BaseUser item : baseUsers) {
            Message message = new Message(sendUser, item, new Date(), null, command.getContent(), MessageType.SYSTEM_MESSAGE, ShowType.SHOW);
            messageRepository.save(message);
        }
    }

    //发送给用户
    @Override
    public Message createByBaseUser(CreateMessageByBaseUserCommand command) {
        BaseUser sendUser = baseUserService.show(command.getSendBaseUser());

        BaseUser receiveBaseUser = baseUserService.show(command.getReceiveBaseUser());
        Message message = new Message(sendUser, receiveBaseUser, new Date(), null, command.getContent(), command.getType(), ShowType.SHOW);
        messageRepository.save(message);
        return message;
    }

    @Override
    public Pagination<Message> pagination(ListMessageCommand command) {

        List<Criterion> criterionList = new ArrayList();

        if (!CoreStringUtils.isEmpty(command.getSendBaseUser())) {
            criterionList.add(Restrictions.like("sendBaseUser.userName", command.getSendBaseUser(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getBeginTime())) {
            criterionList.add(Restrictions.ge("sendDate", CoreDateUtils.parseDate(command.getBeginTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime())) {
            criterionList.add(Restrictions.le("sendDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }

        criterionList.add(Restrictions.eq("showType", ShowType.SHOW));

        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("sendBaseUser", "sendBaseUser");

        List<Order> orderList = new ArrayList();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);

    }

    @Override
    public Message delete(String messageId) {
        Message message = this.getById(messageId);
        message.setShowType(ShowType.BLANK);
        messageRepository.update(message);
        return message;
    }


    @Override
    public Pagination<Message> apiPagination(CompanyListMessageCommand command) {
        Map<String, String> aliasMap = new HashMap<String, String>();
        List<Criterion> criterionList = new ArrayList();
        aliasMap.put("receiveBaseUser", "receiveBaseUser");
        aliasMap.put("sendBaseUser", "sendBaseUser");
        switch (command.getSearchType()) {
            case 1:
                criterionList.add(Restrictions.eq("sendBaseUser.id", command.getCompany()));
                break;
            case 2:
                criterionList.add(Restrictions.like("receiveBaseUser.id", command.getCompany()));
                break;
        }

        if (!CoreStringUtils.isEmpty(command.getBeginTime())) {
            criterionList.add(Restrictions.ge("sendDate", CoreDateUtils.parseDate(command.getBeginTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime())) {
            criterionList.add(Restrictions.le("sendDate", CoreDateUtils.parseDate(command.getEndTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getReceiveBaseUser())) {
            criterionList.add(Restrictions.like("receiveBaseUser.userName", command.getReceiveBaseUser(), MatchMode.ANYWHERE));
        }
        List<Order> orderList = new ArrayList();

        orderList.add(Order.desc("sendDate"));
        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public Pagination<Message> apiAppList(ListMessageCommand command) {
        Map<String, String> aliasMap = new HashMap<String, String>();
        List<Criterion> criterionList = new ArrayList<Criterion>();
        aliasMap.put("receiveBaseUser", "receiveBaseUser");

        if (!CoreStringUtils.isEmpty(command.getReceiveBaseUser())) {
            criterionList.add(Restrictions.eq("receiveBaseUser.id", command.getReceiveBaseUser()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("sendDate"));

        List<Projection> projections = new ArrayList<Projection>();
        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public boolean apiUnread(String id) {
        Map<String, String> aliasMap = new HashMap<String, String>();
        List<Criterion> criterionList = new ArrayList<Criterion>();
        aliasMap.put("receiveBaseUser", "receiveBaseUser");

        criterionList.add(Restrictions.eq("receiveBaseUser.id", id));
        criterionList.add(Restrictions.isNull("receiveDate"));
        return messageRepository.list(criterionList, null, null, null, aliasMap).size() > 0;
    }

    @Override
    public void apiCreate(CompanyCreateMessageCommand command) {

        BaseUser sendUser = baseUserService.show(command.getCompany());

        for (String item : command.getReceiveBaseUser()) {
            item = item.replaceAll("\\[", "");
            item = item.replaceAll("]", "");
            BaseUser receiveBaseUser = baseUserService.show(item);
            Message message = new Message(sendUser, receiveBaseUser, new Date(), null, command.getContent(), MessageType.OTHER_MESSAGE, ShowType.SHOW);
            messageRepository.save(message);
        }
    }


}
