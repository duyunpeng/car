package pengyi.application.moneydetailed.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.moneydetailed.MoneyDetailed;

/**
 * Created by YJH on 2016/3/9.
 */
@Component
public class MoneyDetailedRepresentationMapper extends CustomMapper<MoneyDetailed, MoneyDetailedRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(MoneyDetailed moneyDetailed, MoneyDetailedRepresentation representation, MappingContext context) {
        if (null != moneyDetailed.getBaseUser()) {
            BaseUserRepresentation data = mappingService.map(moneyDetailed.getBaseUser(), BaseUserRepresentation.class,false);
            representation.setBaseUser(data);
        }
    }

}
