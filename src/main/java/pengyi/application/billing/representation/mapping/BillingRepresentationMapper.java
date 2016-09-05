package pengyi.application.billing.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.billing.Billing;

/**
 * Created by YJH on 2016/3/21.
 */
@Component
public class BillingRepresentationMapper extends CustomMapper<Billing, BillingRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Billing billing, BillingRepresentation representation, MappingContext context) {
        if (null != billing.getCompany()) {
            CompanyRepresentation data = mappingService.map(billing.getCompany(), CompanyRepresentation.class, false);
            representation.setCompany(data);
        }
    }

}
