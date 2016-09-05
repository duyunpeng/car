package pengyi.application.evaluate;

import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public interface IEvaluateAppService {

    Pagination<EvaluateRepresentation> pagination(ListEvaluateCommand command);

    void create(CreateEvaluateCommand command);

    void edit(EditEvaluateCommand command);

    EvaluateRepresentation show(String id);

}
