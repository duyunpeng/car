package pengyi.application.user.company;

import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyAppService {

    Pagination<CompanyRepresentation> pagination(BaseListCompanyCommand command);

    Pagination<CompanyRepresentation> paginationList(BaseListCompanyCommand command);

    CompanyRepresentation edit(EditCompanyCommand command);

    CompanyRepresentation show(String id);

    CompanyRepresentation updateStatus(EditStatusCommand command);

    List<CompanyRepresentation> allList();

    CompanyRepresentation create(CreateCompanyCommand command);
}
