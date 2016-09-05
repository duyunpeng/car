package pengyi.domain.service.feedback;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.feedback.command.EditFeedBackCommand;
import pengyi.application.feedback.command.ListFeedBackCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.HandleStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.feedback.FeedBack;
import pengyi.domain.model.feedback.IFeedBackRepository;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("feedBackService")
public class FeedBackService implements IFeedBackService {

    @Autowired
    private IFeedBackRepository<FeedBack, String> feedBackRepository;

    @Override
    public Pagination<FeedBack> pagination(ListFeedBackCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        if (!CoreStringUtils.isEmpty(command.getEmail())) {
            criterionList.add(Restrictions.like("email", command.getEmail(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getPhone())) {
            criterionList.add(Restrictions.like("phone", command.getPhone(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getQq())) {
            criterionList.add(Restrictions.like("qq", command.getQq(), MatchMode.ANYWHERE));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        return feedBackRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public FeedBack create(CreateFeedBackCommand command) {
        FeedBack feedBack = new FeedBack(command.getEmail(), command.getPhone(), command.getQq(), command.getContent(), new Date(), HandleStatus.WAIT_HANDLE);

        feedBackRepository.save(feedBack);

        return feedBack;
    }

    @Override
    public FeedBack edit(EditFeedBackCommand command) {
        FeedBack feedBack = this.show(command.getId());
        feedBack.fainWhenConcurrencyViolation(command.getVersion());

        //TODO 修改内容待定

        return feedBack;
    }

    @Override
    public FeedBack show(String id) {
        FeedBack feedBack = feedBackRepository.getById(id);
        if (null == feedBack) {
            throw new NoFoundException("没有找到意见反馈id=[" + id + "]的记录");
        }
        return feedBack;
    }
}
