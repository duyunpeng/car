package pengyi.application.rescue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.service.rescue.IRescueService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/3/10.
 */
@Service("rescueAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RescueAppService implements IRescueAppService{

    @Autowired
    private IRescueService rescueService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<RescueRepresentation> pagination(ListRescueCommand command) {

        if(null!=command.getApplyUser()){
            command.verifyPage();
            command.verifyPageSize(20);
            Pagination<Rescue> pagination= rescueService.pagination(command);
            List<RescueRepresentation> data= mappingService.mapAsList(pagination.getData(),RescueRepresentation.class);

            return new Pagination<RescueRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
        }else {

            command.verifyPage();
            command.verifyPageSize(20);
            Pagination<Rescue> pagintation=rescueService.pagination(command);
            List<RescueRepresentation>data=mappingService.mapAsList(pagintation.getData(),RescueRepresentation.class);

            return new Pagination<RescueRepresentation>(data,pagintation.getCount(),pagintation.getPage(),pagintation.getPageSize());
        }
    }

    @Override
    public RescueRepresentation create(CreateRescueCommand command) {
        return mappingService.map(rescueService.create(command),RescueRepresentation.class,false);
    }

    @Override
    @Transactional(readOnly = false)
    public RescueRepresentation edit(EditRescueCommand command) {
        return mappingService.map(rescueService.edit(command),RescueRepresentation.class,false);
    }

    @Override

    public RescueRepresentation updateStatus(EditRescueCommand command) {
        return mappingService.map(rescueService.updateStatus(command),RescueRepresentation.class,false);
    }

    @Override
    @Transactional(readOnly = true)
    public RescueRepresentation show(String id) {
        return mappingService.map(rescueService.show(id),RescueRepresentation.class,false);
    }
}
