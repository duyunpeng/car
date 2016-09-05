package pengyi.application.rescue;


import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by LvDi on 2016/3/10.
 */
public interface IRescueAppService {

    Pagination<RescueRepresentation> pagination(ListRescueCommand command);

    RescueRepresentation create(CreateRescueCommand command);

    RescueRepresentation edit(EditRescueCommand command);

    RescueRepresentation updateStatus(EditRescueCommand command);

    RescueRepresentation show(String id);


}
