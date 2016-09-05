package pengyi.application.user.company.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.company.Company;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class CompanyRepresentationMapper extends CustomMapper<Company, CompanyRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Company company, CompanyRepresentation representation, MappingContext context) {
        if (null != company.getRegisterAddress()) {
            AreaRepresentation data = mappingService.map(company.getRegisterAddress(), AreaRepresentation.class, false);
            representation.setRegisterAddress(data);
        }
        if (null != company.getOperateAddress()) {
            AreaRepresentation data = mappingService.map(company.getOperateAddress(), AreaRepresentation.class, false);
            representation.setOperateAddress(data);
        }
    }

}
