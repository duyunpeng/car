package pengyi.application.feedback;

import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IApiFeedBackAppService {
    BaseResponse create(CreateFeedBackCommand command);
}
