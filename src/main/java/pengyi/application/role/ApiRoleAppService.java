package pengyi.application.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.service.role.IRoleService;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiRoleAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiRoleAppService implements IApiRoleAppService {

    @Autowired
    private IRoleService roleService;

}
