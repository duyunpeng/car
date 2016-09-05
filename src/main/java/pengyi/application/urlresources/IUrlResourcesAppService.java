package pengyi.application.urlresources;

import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.command.EditUrlResourcesCommand;
import pengyi.application.urlresources.command.ListUrlResourcesCommand;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUrlResourcesAppService {

    List<UrlResources> findAllUrlResources();

    Pagination<UrlResourcesRepresentation> pagination(ListUrlResourcesCommand command);

    UrlResourcesRepresentation create(CreateUrlResourcesCommand command);

    UrlResourcesRepresentation edit(EditUrlResourcesCommand command);

    UrlResourcesRepresentation show(String id);

    UrlResourcesRepresentation updateStatus(EditStatusCommand command);

}
