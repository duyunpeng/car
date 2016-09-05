package pengyi.core.shiro;

import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import pengyi.application.urlresources.IUrlResourcesAppService;
import pengyi.core.type.EnableStatus;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.urlresources.UrlResources;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/3/7.
 */
public class ShiroFilterChainManager {

    @Autowired
    private CustomDefaultFilterChainManager filterChainManager;

    @Autowired
    private IUrlResourcesAppService urlResourcesAppService;

    public void setUrlResourcesAppService(IUrlResourcesAppService urlResourcesAppService) {
        this.urlResourcesAppService = urlResourcesAppService;
    }

    private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
        initFilterChains(loadAllUrlMatcher());
    }

    public void initFilterChains(List<UrlResources> apiUrlMatcherList) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (UrlResources urlResources : apiUrlMatcherList) {
            String urlName = urlResources.getUrlName();
            if (urlResources.getStatus() == EnableStatus.ENABLE) {
//                //注册roles filter
//                List<RoleRepresentation> roleRepresentationList = urlMatcherRepresentation.getRoleRepresentationList();
//                String roleNames = StringUtils.EMPTY;
//                if (null != roleRepresentationList) {
//                    int size = roleRepresentationList.size();
//                    int i = 0;
//                    for (RoleRepresentation roleRepresentation : roleRepresentationList) {
//                        if (roleRepresentation.getStatus().equals(EnableStatus.ENABLE)) {
//                            if (i == size - 1) {
//                                roleNames += roleRepresentation.getRoleName();
//                            } else {
//                                roleNames += roleRepresentation.getRoleName() + ",";
//                            }
//                        }
//                        i++;
//                    }
//                    if (!roleNames.isEmpty()) {
//                        filterChainManager.addToChain(url, "roles", roleNames);
//                    }
//                }
                //注册perms filter
                List<Permission> permissionList = urlResources.getUrlPermission();
                if (null != permissionList) {
                    for (Permission permission : permissionList) {
                        if (permission.getStatus() == EnableStatus.ENABLE) {
                            filterChainManager.addToChain(urlName, "perms", permission.getPermissionName());
                        }
                    }
                }
            }
        }
    }

    public List<UrlResources> loadAllUrlMatcher() {
        try {
            return urlResourcesAppService.findAllUrlResources();
        } catch (Exception e) {
            //加载失败 重新加载
            return loadAllUrlMatcher();
        }
    }

}
