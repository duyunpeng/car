package pengyi.domain.service.message;

import pengyi.application.message.command.*;
import pengyi.domain.model.message.Message;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IMessageService {

    Message getById(String messageId);

    void create(CreateMessageByRoleCommand command);

    Message createByBaseUser(CreateMessageByBaseUserCommand command);

    Pagination<Message> pagination(ListMessageCommand command);

    Message delete(String messageId);

    //以下是api方法
    void apiCreate(CompanyCreateMessageCommand command);

    Message show(String messageId);

    Pagination<Message> apiPagination(CompanyListMessageCommand command);

    Pagination<Message> apiAppList(ListMessageCommand command);

    boolean apiUnread(String id);
}
