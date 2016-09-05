package pengyi.application.recharge.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.recharge.representation.RechargeRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.recharge.Recharge;

/**
 * Created by pengyi on 2016/4/11.
 */
@Component
public class RechargeRepresentationMapper extends CustomMapper<Recharge, RechargeRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Recharge recharge, RechargeRepresentation representation, MappingContext context) {
        if(null != recharge.getUser()){
            BaseUserRepresentation data = mappingService.map(recharge.getUser(),BaseUserRepresentation.class,false);
            representation.setUser(data);
        }
    }

}
