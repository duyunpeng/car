package pengyi.application.withhold;

import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.application.withhold.representation.WithholdRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

/**
 * Created by pengyi on 2016/5/7.
 */
public interface IWithholdAppService {
    void create(CreateWithholdCommand command);

    Pagination<WithholdRepresentation> pagination(ListWithholdCommand command);

    WithholdRepresentation show(String id);

    BaseResponse apiCreate(CreateWithholdCommand command);

    BaseResponse apiList(ListWithholdCommand command);
}
