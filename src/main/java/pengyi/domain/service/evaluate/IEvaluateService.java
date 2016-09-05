package pengyi.domain.service.evaluate;

import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.repository.generic.Pagination;

import java.util.List;


/**
 * Created by lvdi on 2016/3/8.
 */
public interface IEvaluateService {


    Pagination<Evaluate> pagination(ListEvaluateCommand command);


    void edit(EditEvaluateCommand command);

    Evaluate show(String id);

    List<Evaluate> searchByOrder(String orderId);

    void create(CreateEvaluateCommand command);

    Double reckonDriverLevel(String driverId);

    /*********
     * api方法 yjh
     ***************/
    Evaluate apiCreateEvaluate(CreateEvaluateCommand command);

    void delete(String orderId);
}
