package pengyi.application.moneydetailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.EditMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("moneyDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class MoneyDetailedAppService implements IMoneyDetailedAppService {

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<MoneyDetailed> pagination = moneyDetailedService.pagination(command);
        List<MoneyDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), MoneyDetailedRepresentation.class);
        return new Pagination<MoneyDetailedRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public MoneyDetailedRepresentation create(CreateMoneyDetailedCommand command) {
        return mappingService.map(moneyDetailedService.create(command), MoneyDetailedRepresentation.class, false);
    }

    @Override
    public MoneyDetailedRepresentation edit(EditMoneyDetailedCommand command) {
        return mappingService.map(moneyDetailedService.edit(command), MoneyDetailedRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public MoneyDetailedRepresentation show(String id) {
        return mappingService.map(moneyDetailedService.show(id), MoneyDetailedRepresentation.class, false);
    }

    @Override
    public List<MoneyDetailedRepresentation> exportExcel(ListMoneyDetailedCommand command) {
        return mappingService.mapAsList(moneyDetailedService.apiexportExcel(command), MoneyDetailedRepresentation.class);

    }
}
