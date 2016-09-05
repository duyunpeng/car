package pengyi.domain.service.user.company;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.IllegalOperationException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.core.upload.IFileUploadService;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.company.ICompanyRepository;
import pengyi.domain.service.area.IAreaService;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyService")
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository<Company, String> companyRepository;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<Company> pagination(BaseListCompanyCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (!CoreStringUtils.isEmpty(command.getName())) {
            criteriaList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return companyRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, orderList);
    }

    @Override
    public Company edit(EditCompanyCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        String folder = command.getFolder().replaceAll("img_tmp", "img");

        company.setName(command.getName());
        company.setRegisterAddress(registerAddress);
        company.setOperateAddress(operateAddress);
        company.setFolder(folder);

        companyRepository.update(company);

        fileUploadService.move(folder.substring(folder.lastIndexOf("/") + 1));
        return company;
    }

    @Override
    public Company show(String id) {
        Company company = companyRepository.getById(id);
        if (null == company) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return company;
    }

    @Override
    public Company create(Company company) {
        companyRepository.save(company);
        return company;
    }

    @Override
    public Company terraceCreate(CreateCompanyCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        Role role = roleService.searchByName("company");

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        String folder = command.getFolder().replaceAll("img_tmp", "img");

        Company company = new Company(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.COMPANY, command.getName(),
                folder, CoreDateUtils.parseDate(command.getRegisterDate(), "yyyy/MM/dd HH:mm"), registerAddress, operateAddress, 0.0);

        companyRepository.save(company);
        fileUploadService.move(folder.substring(folder.lastIndexOf("/") + 1));
        return company;
    }

    @Override
    public Company updateStatus(EditStatusCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        company.setStatus(EnableStatus.ENABLE);

        companyRepository.update(company);

        return company;
    }

    @Override
    public List<Company> list(BaseListCompanyCommand companyCommand) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("status", companyCommand.getStatus()));

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));

        return companyRepository.list(criterionList, orderList);
    }

    @Override
    public void update(Company company) {
        companyRepository.update(company);
    }

    @Override
    public void addLock() {
        companyRepository.addLock();
    }

    @Override
    public Company apiEdit(EditCompanyCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

//        Area registerAddress = areaService.show(command.getRegisterAddress());
//        Area operateAddress = areaService.show(command.getOperateAddress());

        company.setEmail(command.getEmail());
        company.setName(command.getName());
//        company.setRegisterAddress(registerAddress);
//        company.setOperateAddress(operateAddress);

        companyRepository.update(company);
        return company;
    }

    @Override
    public void apiUpdateFolder(UpdateFolderCommand command) {
        Company company = this.show(command.getId());

        company.setFolder(command.getFolder());

        companyRepository.update(company);
    }

    @Override
    public Company apiCreate(CreateCompanyCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        String folder = command.getFolder().replaceAll("img_tmp", "img");

        Role role = roleService.searchByName("company");

        Company company = new Company(command.getUserName(), password, salt, EnableStatus.DISABLE, new BigDecimal(0), new Date(),
                role, command.getEmail(), UserType.COMPANY, command.getName(), folder,
                CoreDateUtils.parseDate(command.getRegisterDate(), "yyyy/MM/dd HH:mm"), registerAddress, operateAddress, 0.0);

        companyRepository.save(company);

        fileUploadService.move(folder.substring(folder.lastIndexOf("/") + 1));

        return company;
    }

    @Override
    public Company apiUpdatePassword(UpdatePasswordCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        if (!PasswordHelper.equalsPassword(company, command.getOldPassword())) {
            throw new IllegalOperationException("旧密码错误");
        }

        String password = PasswordHelper.encryptPassword(command.getNewPassword(), company.getCredentialsSalt());

        company.setPassword(password);

        companyRepository.update(company);

        return company;
    }

    @Override
    public List<Company> apiByName(String name) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(name)) {
            criteriaList.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        }

        return companyRepository.list(criteriaList, null);
    }

    @Override
    public Company apiResetPassword(ResetPasswordCommand command) {
        Company company = (Company) baseUserService.searchByUserName(command.getUserName());

        String password = PasswordHelper.encryptPassword(command.getPassword(), company.getCredentialsSalt());
        command.setPassword(password);

        companyRepository.update(company);
        return company;
    }
}
