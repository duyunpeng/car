package pengyi.domain.service.withhold;

import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.domain.model.withhold.Withhold;
import pengyi.repository.generic.Pagination;

/**
 * Created by pengyi on 2016/5/7.
 */
public interface IWithHoldService {
    void create(CreateWithholdCommand command);

    Pagination<Withhold> pagination(ListWithholdCommand command);

    Withhold show(String id);
}
