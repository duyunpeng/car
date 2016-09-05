package pengyi.application.evaluate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.service.evaluate.IEvaluateService;
import pengyi.repository.generic.Pagination;

import java.util.List;


/**
 * Created by ${lvdi} on 2016/3/8.
 */
@Service("evaluateAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class EvaluateAppService implements IEvaluateAppService {

    @Autowired
    private IEvaluateService evaluateService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<EvaluateRepresentation> pagination(ListEvaluateCommand command) {
        if (null != command.getOrder()) {
            command.verifyPage();
            command.verifyPageSize(20);
            Pagination<Evaluate> pagination = evaluateService.pagination(command);
            List<EvaluateRepresentation> data = mappingService.mapAsList(pagination.getData(), EvaluateRepresentation.class);
            return new Pagination<EvaluateRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
        } else {
            command.verifyPage();
            command.verifyPageSize(20);
            Pagination<Evaluate> pagination = evaluateService.pagination(command);
            List<EvaluateRepresentation> data = mappingService.mapAsList(pagination.getData(), EvaluateRepresentation.class);
            return new Pagination<EvaluateRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void create(CreateEvaluateCommand command) {
        evaluateService.create(command);
    }

    @Override
    @Transactional(readOnly = false)
    public void edit(EditEvaluateCommand command) {
        evaluateService.edit(command);
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluateRepresentation show(String id) {
        return mappingService.map(evaluateService.show(id), EvaluateRepresentation.class, false);
    }

}
