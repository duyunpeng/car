package pengyi.domain.service.urlresources;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.command.EditUrlResourcesCommand;
import pengyi.application.urlresources.command.ListUrlResourcesCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.shiro.ShiroFilterChainManager;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.urlresources.IUrlResourcesRepository;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.domain.service.permission.IPermissionService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("urlResourcesService")
public class UrlResourcesService implements IUrlResourcesService {

    @Autowired
    private IUrlResourcesRepository<UrlResources, String> urlResourcesRepository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public List<UrlResources> findAllUrlResources() {
        return urlResourcesRepository.findAll();
    }

    @Override
    public Pagination<UrlResources> pagination(ListUrlResourcesCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUrlName())) {
            criteriaList.add(Restrictions.like("urlName", command.getUrlName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return urlResourcesRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public UrlResources create(CreateUrlResourcesCommand command) {
        if (null != this.searchByName(command.getUrlName())) {
            throw new ExistException("路径名[" + command.getUrlName() + "]的记录已存在");
        }

        List<Permission> permissions = permissionService.getPermissionsByIds(command.getUrlPermission());

        UrlResources urlResources = new UrlResources(command.getUrlName(), command.getDescription(), permissions, command.getStatus());

        urlResourcesRepository.save(urlResources);

        return urlResources;
    }

    @Override
    public UrlResources edit(EditUrlResourcesCommand command) {
        UrlResources urlResources = this.show(command.getId());

        urlResources.fainWhenConcurrencyViolation(command.getVersion());

        if (!urlResources.getUrlName().equals(command.getUrlName())) {
            if (null != this.searchByName(command.getUrlName())) {
                throw new ExistException("路径名[" + command.getUrlName() + "]的记录已存在");
            }
        }

        List<Permission> permissions = permissionService.getPermissionsByIds(command.getUrlPermission());

        urlResources.setUrlName(command.getUrlName());
        urlResources.setDescription(command.getDescription());
        urlResources.setUrlPermission(permissions);

        urlResourcesRepository.update(urlResources);

        return urlResources;
    }

    @Override
    public UrlResources show(String id) {
        UrlResources urlResources = urlResourcesRepository.getById(id);
        if (null == urlResources) {
            throw new NoFoundException("没有找到资源路径id=[" + id + "]的记录");
        }
        return urlResources;
    }

    @Override
    public UrlResources updateStatus(EditStatusCommand command) {
        UrlResources urlResources = this.show(command.getId());
        urlResources.fainWhenConcurrencyViolation(command.getVersion());
        if (urlResources.getStatus() == EnableStatus.ENABLE) {
            urlResources.setStatus(EnableStatus.DISABLE);
        } else {
            urlResources.setStatus(EnableStatus.ENABLE);
        }

        urlResourcesRepository.update(urlResources);

        return urlResources;
    }

    @Override
    public UrlResources searchByName(String urlResourcesName) {
        return urlResourcesRepository.getByName(urlResourcesName);
    }


}
