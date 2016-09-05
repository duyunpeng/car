package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.*;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/8.
 */
@Service("messageAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class MessageAppService implements IMessageAppService {
    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;


    @Override//返回paginationList并将ListMessageCommand转换成MessageRepresentation
    @Transactional(readOnly = true)
    public Pagination<MessageRepresentation> pagination(ListMessageCommand command) {
        command.verifyPage();
        command.verifyPageSize(12);
        Pagination<Message> pagination = messageService.pagination(command);
        List<MessageRepresentation> data = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);
        return new Pagination<MessageRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override//返回发送的信息
    public void create(CreateMessageByRoleCommand command) {
        messageService.create(command);
    }


    @Override
    public MessageRepresentation delete(String messageId) {
        return mappingService.map(messageService.delete(messageId), MessageRepresentation.class, false);
    }

    @Override
    public MessageRepresentation createByBaseUser(CreateMessageByBaseUserCommand command) {
        return mappingService.map(messageService.createByBaseUser(command), MessageRepresentation.class, false);
    }


    @Override
    public MessageRepresentation show(String messageId) {

        return mappingService.map(messageService.getById(messageId), MessageRepresentation.class, false);
    }


}
