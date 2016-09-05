package pengyi.domain.service.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.*;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.exception.NotEqualException;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IBaseUserRepository;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.terrace.Terrace;
import pengyi.domain.model.user.user.User;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.domain.service.user.terrace.ITerraceService;
import pengyi.domain.service.user.user.IUserService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("baseUserService")
public class BaseUserService implements IBaseUserService {

    @Autowired
    private IBaseUserRepository<BaseUser, String> baseUserRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IDriverService driverService;

    @Autowired
    private ITerraceService terraceService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICompanyService companyService;

    @Override
    public BaseUser searchByUserName(String userName) {
        return baseUserRepository.getByUserName(userName);
    }

    @Override
    public Pagination<BaseUser> pagination(BaseListBaseUserCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }

        if (null != command.getUserType()) {
            criteriaList.add(Restrictions.eq("userType", command.getUserType()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return baseUserRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, orderList);
    }

    @Override
    public BaseUser resetPassword(ResetPasswordCommand command) {
        BaseUser baseUser = this.show(command.getId());

        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        String newPassword = PasswordHelper.encryptPassword(command.getPassword(), baseUser.getCredentialsSalt());

        baseUser.setPassword(newPassword);

        baseUserRepository.update(baseUser);
        return baseUser;
    }

    @Override
    public BaseUser show(String id) {
        BaseUser baseUser = baseUserRepository.getById(id);
        if (null == baseUser) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return baseUser;
    }

    @Override
    public BaseUser create(BaseCreateBaseUserCommand command) {
        if (null != this.searchByUserName(command.getUserName())) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }

        Role role = roleService.show(command.getUserRole());


        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        if (role.getRoleName().equals("user")) {
            command.setUserType(UserType.USER);
            User user = new User(command.getUserName(), password, salt, command.getStatus(), new BigDecimal(0), new Date(),
                    role, command.getEmail(), command.getUserType());
            return userService.create(user);
        } else if (role.getRoleName().equals("company")) {
            command.setUserType(UserType.COMPANY);
            Company company = new Company(command.getUserName(), password, salt, command.getStatus(), new BigDecimal(0), new Date(),
                    role, command.getEmail(), command.getUserType());
            return companyService.create(company);
        } else if (role.getRoleName().equals("terrace")) {
            command.setUserType(UserType.TERRACE);
            Terrace terrace = new Terrace(command.getUserName(), password, salt, command.getStatus(), new BigDecimal(0), new Date(),
                    role, command.getEmail(), command.getUserType());
            return terraceService.create(terrace);
        } else {
            command.setUserType(UserType.DRIVER);
            Driver driver = new Driver(command.getUserName(), password, salt, command.getStatus(), new BigDecimal(0), new Date(),
                    role, command.getEmail(), command.getUserType());
            return driverService.create(driver);
        }
    }

    @Override
    public BaseUser login(LoginUserCommand command) {
        BaseUser baseUser = baseUserRepository.getByUserName(command.getUsername());
        if (null == baseUser) {
            throw new UnknownAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(command.getUsername(), command.getPassword());
        subject.login(token);
        return baseUser;
    }

    @Override
    public List<BaseUser> searchByUserRole(String roleId) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        criterionList.add(Restrictions.eq("userRole.id", roleId));

        return baseUserRepository.list(criterionList, null);
    }

    @Override
    public void addBalance(String userId, BigDecimal bigDecimal) {
        BaseUser baseUser = baseUserRepository.getById(userId);
        baseUser.setBalance(baseUser.getBalance().add(bigDecimal));
        baseUserRepository.update(baseUser);
    }

    @Override
    public void subtractBalance(String userId, BigDecimal bigDecimal) {
        BaseUser baseUser = baseUserRepository.getById(userId);
        baseUser.setBalance(baseUser.getBalance().subtract(bigDecimal));
        baseUserRepository.update(baseUser);
    }


    @Override
    public BaseUser updateStatus(EditStatusCommand command) {
        BaseUser baseUser = this.show(command.getId());
        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        if (baseUser.getStatus() == EnableStatus.ENABLE) {
            baseUser.setStatus(EnableStatus.DISABLE);
        } else {
            baseUser.setStatus(EnableStatus.ENABLE);
        }

        baseUserRepository.update(baseUser);

        return baseUser;
    }

    @Override
    public BaseUser updateBaseUserRole(EditBaseUserRoleCommand command) {
        BaseUser baseUser = this.show(command.getId());
        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        if (!CoreStringUtils.isEmpty(command.getUserRole())) {
            Role role = roleService.show(command.getUserRole());
            baseUser.setUserRole(role);
        } else {
            baseUser.setUserRole(null);
        }

        baseUserRepository.update(baseUser);
        return baseUser;
    }

    @Override
    public BaseUser apiResetPassword(ResetPasswordCommand command) {
        BaseUser baseUser = this.searchByUserName(command.getUserName());
        String newPassword = PasswordHelper.encryptPassword(command.getPassword(), baseUser.getCredentialsSalt());

        baseUser.setPassword(newPassword);

        baseUserRepository.update(baseUser);
        return baseUser;
    }

    @Override
    public List<BaseUser> apiSearchByUserNameAndRole(BaseListBaseUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("userName", command.getUserName()));
        criterionList.add(Restrictions.eq("role.roleName", command.getRoleName()));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("userRole", "role");
        return baseUserRepository.list(criterionList, null, null, null, alias);
    }
}
