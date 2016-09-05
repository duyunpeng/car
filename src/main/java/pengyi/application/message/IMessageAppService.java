package pengyi.application.message;

import pengyi.application.message.command.*;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/8.
 */
public interface IMessageAppService {

    Pagination<MessageRepresentation> pagination(ListMessageCommand command);

    void create(CreateMessageByRoleCommand command);

    MessageRepresentation show(String id);

    MessageRepresentation delete(String messageId);

    MessageRepresentation createByBaseUser(CreateMessageByBaseUserCommand command);


}
