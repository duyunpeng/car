package pengyi.application.urlresources;

import pengyi.application.urlresources.representation.UrlResourcesRepresentation;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiUrlResourcesAppService {
    List<UrlResourcesRepresentation> allList();
}
