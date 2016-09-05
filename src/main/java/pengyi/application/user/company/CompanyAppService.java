package pengyi.application.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.EnableStatus;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CompanyAppService implements ICompanyAppService {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<CompanyRepresentation> pagination(BaseListCompanyCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Company> pagination = companyService.pagination(command);
        List<CompanyRepresentation> data = mappingService.mapAsList(pagination.getData(), CompanyRepresentation.class);
        return new Pagination<CompanyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<CompanyRepresentation> paginationList(BaseListCompanyCommand command) {
        command.verifyPage();
        Pagination<Company> pagination = companyService.pagination(command);
        List<CompanyRepresentation> data = mappingService.mapAsList(pagination.getData(), CompanyRepresentation.class);
        return new Pagination<CompanyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public CompanyRepresentation edit(EditCompanyCommand command) {
        return mappingService.map(companyService.edit(command), CompanyRepresentation.class, false);
    }

    @Override
    public CompanyRepresentation show(String id) {
        return mappingService.map(companyService.show(id), CompanyRepresentation.class, false);
    }

    @Override
    public CompanyRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(companyService.updateStatus(command), CompanyRepresentation.class, false);
    }

    @Override
    public List<CompanyRepresentation> allList() {
        BaseListCompanyCommand companyCommand = new BaseListCompanyCommand();
        companyCommand.setStatus(EnableStatus.ENABLE);
        return mappingService.mapAsList(companyService.list(companyCommand), CompanyRepresentation.class);
    }

    @Override
    public CompanyRepresentation create(CreateCompanyCommand command) {
        return mappingService.map(companyService.terraceCreate(command), CompanyRepresentation.class, false);
    }
}
