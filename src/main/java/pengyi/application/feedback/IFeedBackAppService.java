package pengyi.application.feedback;

import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.feedback.command.EditFeedBackCommand;
import pengyi.application.feedback.command.ListFeedBackCommand;
import pengyi.application.feedback.representation.FeedBackRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IFeedBackAppService {

    Pagination<FeedBackRepresentation> pagination(ListFeedBackCommand command);

    FeedBackRepresentation create(CreateFeedBackCommand command);

    FeedBackRepresentation edit(EditFeedBackCommand command);

    FeedBackRepresentation show(String id);

}
