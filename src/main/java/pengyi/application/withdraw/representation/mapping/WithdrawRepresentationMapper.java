package pengyi.application.withdraw.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import pengyi.application.withdraw.representation.WithdrawRepresentation;
import pengyi.domain.model.withdraw.Withdraw;

/**
 * Created by pengyi on 2016/5/6.
 */
@Component
public class WithdrawRepresentationMapper extends CustomMapper<Withdraw, WithdrawRepresentation> {

    public void mapAtoB(Withdraw withdraw, WithdrawRepresentation representation, MappingContext context) {
        representation.setUserId(withdraw.getBaseUser().getId());
        representation.setUsername(withdraw.getBaseUser().getUserName());
    }

}
