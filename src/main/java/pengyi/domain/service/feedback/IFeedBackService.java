package pengyi.domain.service.feedback;

import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.feedback.command.EditFeedBackCommand;
import pengyi.application.feedback.command.ListFeedBackCommand;
import pengyi.domain.model.feedback.FeedBack;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IFeedBackService {

    Pagination<FeedBack> pagination(ListFeedBackCommand command);

    FeedBack create(CreateFeedBackCommand command);

    FeedBack edit(EditFeedBackCommand command);

    FeedBack show(String id);

}
