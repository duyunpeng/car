package pengyi.application.urlresources.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.urlresources.UrlResources;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class UrlResourcesRepresentationMapper extends CustomMapper<UrlResources, UrlResourcesRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(UrlResources urlResources, UrlResourcesRepresentation representation, MappingContext context){
        if(null != urlResources.getUrlPermission()){
            List<PermissionRepresentation> data = mappingService.mapAsList(urlResources.getUrlPermission(),PermissionRepresentation.class);
            representation.setUrlPermission(data);
        }
    }
}
