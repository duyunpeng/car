package pengyi.application.urlresources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.urlresources.IUrlResourcesService;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiUrlResourcesAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiUrlResourcesAppService implements IApiUrlResourcesAppService {

    @Autowired
    private IUrlResourcesService urlResourcesService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public List<UrlResourcesRepresentation> allList() {

        return mappingService.mapAsList(urlResourcesService.findAllUrlResources(), UrlResourcesRepresentation.class);
    }
}
