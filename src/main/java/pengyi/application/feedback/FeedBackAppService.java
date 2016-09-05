package pengyi.application.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.feedback.command.EditFeedBackCommand;
import pengyi.application.feedback.command.ListFeedBackCommand;
import pengyi.application.feedback.representation.FeedBackRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.feedback.FeedBack;
import pengyi.domain.service.feedback.IFeedBackService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("feedBackAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class FeedBackAppService implements IFeedBackAppService {

    @Autowired
    private IFeedBackService feedBackService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<FeedBackRepresentation> pagination(ListFeedBackCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<FeedBack> pagination = feedBackService.pagination(command);
        List<FeedBackRepresentation> data = mappingService.mapAsList(pagination.getData(), FeedBackRepresentation.class);
        return new Pagination<FeedBackRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public FeedBackRepresentation create(CreateFeedBackCommand command) {
        return mappingService.map(feedBackService.create(command), FeedBackRepresentation.class, false);
    }

    @Override
    public FeedBackRepresentation edit(EditFeedBackCommand command) {
        return mappingService.map(feedBackService.edit(command), FeedBackRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedBackRepresentation show(String id) {
        return mappingService.map(feedBackService.show(id), FeedBackRepresentation.class, false);
    }
}
