package pengyi.domain.service.urlresources;

import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.command.EditUrlResourcesCommand;
import pengyi.application.urlresources.command.ListUrlResourcesCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUrlResourcesService {
    List<UrlResources> findAllUrlResources();

    Pagination<UrlResources> pagination(ListUrlResourcesCommand command);

    UrlResources create(CreateUrlResourcesCommand command);

    UrlResources edit(EditUrlResourcesCommand command);

    UrlResources show(String id);

    UrlResources updateStatus(EditStatusCommand command);

    UrlResources searchByName(String urlResourcesName);
}
