package pengyi.application.message;

import pengyi.application.message.command.*;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/15.
 */
public interface IApiMessageAppService {
    BaseResponse show(String messageId);

    MessageRepresentation deleteByCompany(String messageId);

    BaseResponse apiCreateMessage(CompanyCreateMessageCommand command);

    BaseResponse companyMessageList(CompanyListMessageCommand command);

    BaseResponse list(ListMessageCommand command);

    BaseResponse apiUnread(String id);
}
