package pengyi.application.message.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;

/**
 * Created by liubowen on 2016/3/8.
 */
@Component
public class MessageRepresentationMapper extends CustomMapper<Message, MessageRepresentation> {
    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Message message, MessageRepresentation representation, MappingContext context) {

        if (null != message.getReceiveBaseUser()) {

            BaseUserRepresentation date = mappingService.map(message.getReceiveBaseUser(), BaseUserRepresentation.class, false);

            representation.setReceiveBaseUser(date);
        }
        if(null !=message.getSendBaseUser()){

            BaseUserRepresentation date = mappingService.map(message.getSendBaseUser(),BaseUserRepresentation.class,false);

            representation.setSendBaseUser(date);
        }

    }

}
