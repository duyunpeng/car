package pengyi.application.evaluate;

import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.api.BaseResponse;

/**
 * Created by LvDi on 2016/3/17.
 */
public interface IApiEvaluateAppService {
    BaseResponse createEvaluate(CreateEvaluateCommand command);

    EvaluateRepresentation show(String orderId);

    BaseResponse edit(EditEvaluateCommand command);

    BaseResponse delete(String orderId);

    BaseResponse getByOrderId(String orderId);
}
