package pengyi.domain.service.user.driver;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.driver.command.*;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.AuthStatus;
import pengyi.core.type.DriverType;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.core.upload.IFileUploadService;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;
import pengyi.domain.service.car.ICarService;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverService")
public class DriverService implements IDriverService {

    @Autowired
    private IDriverRepository<Driver, String> driverRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<Driver> pagination(BaseListDriverCommand command) {
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
        return driverRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, orderList);
    }

    @Override
    public Driver edit(EditDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        Company company = companyService.show(command.getCompany());

        driver.setName(command.getName());
        driver.setCompany(company);
        driver.setDriverType(command.getDriverType());
        String identityCarPic = command.getIdentityCardPic().replaceAll("img_tmp", "img");
        String drivingLicencePic = command.getDrivingLicencePic().replaceAll("img_tmp", "img");
        driver.setIdentityCardPic(identityCarPic);
        driver.setDrivingLicencePic(drivingLicencePic);
        driver.setBankCardNo(command.getBankCardNo());
        driver.setBankName(command.getBankName());
        Car car = carService.searchByDriver(driver.getId());
        if (driver.getDriverType() == DriverType.LIMOUSINE) {
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setTravelPic(travelPic);
        } else if (driver.getDriverType() == DriverType.GENERATION) {
            driver.setDrivingLicenceType(command.getDrivingLicenceType());
            if (null != car) {
                carService.delete(car);
            }
        } else {
            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setBusinessPic(businessPic);
            driver.setWorkPic(workPic);
            driver.setTravelPic(travelPic);
            if (null != car) {
                car.setCarType(null);
                carService.update(car);
            }
        }

        driverRepository.update(driver);

        fileUploadService.move(identityCarPic.substring(identityCarPic.lastIndexOf("/") + 1));
        fileUploadService.move(drivingLicencePic.substring(drivingLicencePic.lastIndexOf("/") + 1));
        if (!CoreStringUtils.isEmpty(command.getTravelPic())) {
            fileUploadService.move(command.getTravelPic().substring(command.getTravelPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getBusinessPic())) {
            fileUploadService.move(command.getBusinessPic().substring(command.getBusinessPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getWorkPic())) {
            fileUploadService.move(command.getWorkPic().substring(command.getWorkPic().lastIndexOf("/") + 1));
        }

        return driver;
    }

    @Override
    public Driver show(String id) {
        Driver driver = driverRepository.getById(id);
        if (null == driver) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return driver;
    }

    @Override
    public Driver create(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public Driver terraceCreate(CreateDriverCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        Company company = companyService.show(command.getCompany());

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        String identityCarPic = command.getIdentityCardPic().replaceAll("img_tmp", "img");
        String drivingLicencePic = command.getDrivingLicencePic().replaceAll("img_tmp", "img");

        Role role = roleService.searchByName("driver");
        Driver driver;
        if (command.getDriverType() == DriverType.LIMOUSINE) {
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, travelPic, null, command.getPhone(), null, null,
                    command.getBankCardNo(), command.getBankName());
        } else if (command.getDriverType() == DriverType.GENERATION) {
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, null, command.getDrivingLicenceType(), command.getPhone(), null, null,
                    command.getBankCardNo(), command.getBankName());
        } else {
            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, travelPic, null, command.getPhone(), businessPic, workPic,
                    command.getBankCardNo(), command.getBankName());
        }

        driverRepository.save(driver);
        fileUploadService.move(identityCarPic.substring(identityCarPic.lastIndexOf("/") + 1));
        fileUploadService.move(drivingLicencePic.substring(drivingLicencePic.lastIndexOf("/") + 1));
        if (!CoreStringUtils.isEmpty(command.getTravelPic())) {
            fileUploadService.move(command.getTravelPic().substring(command.getTravelPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getBusinessPic())) {
            fileUploadService.move(command.getBusinessPic().substring(command.getBusinessPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getWorkPic())) {
            fileUploadService.move(command.getWorkPic().substring(command.getWorkPic().lastIndexOf("/") + 1));
        }
        return driver;
    }

    @Override
    public void addLock() {
        driverRepository.addLock();
    }

    @Override
    public List<Driver> searchByCompany(String company) {
        return driverRepository.searchByCompany(company);
    }

    @Override
    public void updateDriverLevel(String driverId, Double level) {
        Driver driver = this.show(driverId);
        driver.setLevel(level);
        driverRepository.update(driver);
    }

    @Override
    public Driver searchByUserName(String userName) {
        return driverRepository.searchByUserName(userName);
    }

    @Override
    public void updateReportCount(String driverId) {
        Driver driver = this.show(driverId);
        driver.setReportCount(driver.getReportCount() + 1);
        driverRepository.update(driver);
    }

    @Override
    public void update(Driver driver) {
        driverRepository.update(driver);
    }

    @Override
    public Driver auth(EditStatusCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setAuthStatus(AuthStatus.AUTH_TERRACE);
        driver.setStatus(EnableStatus.ENABLE);
        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Pagination<Driver> apiPagination(CompanyDriverListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("company.id", command.getCompany()));

        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        if (null != command.getAuthStatus() && command.getAuthStatus() != AuthStatus.ALL) {
            criterionList.add(Restrictions.eq("authStatus", command.getAuthStatus()));
        }

        return driverRepository.pagination(command.getPage(), command.getPageSize(), criterionList, null);
    }

    @Override
    public Driver apiCompanyEditDriver(CompanyDriverEditCommand command) {

        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setName(command.getName());
        driver.setDriverType(command.getDriverType());
        String identityCarPic = command.getIdentityCardPic().replaceAll("img_tmp", "img");
        String drivingLicencePic = command.getDrivingLicencePic().replaceAll("img_tmp", "img");
        driver.setIdentityCardPic(identityCarPic);
        driver.setDrivingLicencePic(drivingLicencePic);
        driver.setBankCardNo(command.getBankCardNo());
        driver.setBankName(command.getBankName());
        if (command.getDriverType() == DriverType.LIMOUSINE) {
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setTravelPic(travelPic);
        } else if (command.getDriverType() == DriverType.GENERATION) {
            driver.setDrivingLicenceType(command.getDrivingLicenceType());
        } else {
            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setBusinessPic(businessPic);
            driver.setWorkPic(workPic);
            driver.setTravelPic(travelPic);
        }

        driverRepository.update(driver);

        fileUploadService.move(identityCarPic.substring(identityCarPic.lastIndexOf("/") + 1));
        fileUploadService.move(drivingLicencePic.substring(drivingLicencePic.lastIndexOf("/") + 1));
        if (!CoreStringUtils.isEmpty(command.getTravelPic())) {
            fileUploadService.move(command.getTravelPic().substring(command.getTravelPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getBusinessPic())) {
            fileUploadService.move(command.getBusinessPic().substring(command.getBusinessPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getWorkPic())) {
            fileUploadService.move(command.getWorkPic().substring(command.getWorkPic().lastIndexOf("/") + 1));
        }
        return driver;
    }

    @Override
    public Driver apiCompanyAuditingDriver(CompanyAuditingDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setAuthStatus(AuthStatus.AUTH_COMPANY);

        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Driver apiCompanyEditStatusDriver(EditStatusCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        if (driver.getStatus() == EnableStatus.ENABLE) {
            driver.setStatus(EnableStatus.DISABLE);
        } else {
            driver.setStatus(EnableStatus.ENABLE);
        }

        driverRepository.update(driver);

        return driver;
    }

    @Override
    public Driver apiCompanyCreateDriver(CreateDriverCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        Company company = companyService.show(command.getCompany());

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        String identityCarPic = command.getIdentityCardPic().replaceAll("img_tmp", "img");
        String drivingLicencePic = command.getDrivingLicencePic().replaceAll("img_tmp", "img");

        Role role = roleService.searchByName("driver");
        Driver driver;
        if (command.getDriverType() == DriverType.LIMOUSINE) {
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, travelPic, null, command.getPhone(), null, null,
                    command.getBankCardNo(), command.getBankName());
        } else if (command.getDriverType() == DriverType.GENERATION) {
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, null, command.getDrivingLicenceType(), command.getPhone(), null, null,
                    command.getBankCardNo(), command.getBankName());
        } else {
            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy/MM/dd HH:mm"), AuthStatus.AUTH_TERRACE, travelPic, null, command.getPhone(), businessPic, workPic,
                    command.getBankCardNo(), command.getBankName());
        }

        driverRepository.save(driver);
        fileUploadService.move(identityCarPic.substring(identityCarPic.lastIndexOf("/") + 1));
        fileUploadService.move(drivingLicencePic.substring(drivingLicencePic.lastIndexOf("/") + 1));
        if (!CoreStringUtils.isEmpty(command.getTravelPic())) {
            fileUploadService.move(command.getTravelPic().substring(command.getTravelPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getBusinessPic())) {
            fileUploadService.move(command.getBusinessPic().substring(command.getBusinessPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getWorkPic())) {
            fileUploadService.move(command.getWorkPic().substring(command.getWorkPic().lastIndexOf("/") + 1));
        }
        return driver;
    }

    @Override
    public Driver apiCompanyExpelDriver(EditStatusCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setCompany(null);

        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Driver apiRegister(RegisterDriverCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }

        Company company = companyService.show(command.getCompany());

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        String identityCarPic = command.getIdentityCardPic().replaceAll("img_tmp", "img");
//        String drivingLicencePic = command.getDrivingLicencePic().replaceAll("img_tmp", "img");

        Role role = roleService.searchByName("driver");
        Driver driver = new Driver(command.getUserName(), password, salt, EnableStatus.DISABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, null,
                null, AuthStatus.AUTH_WAIT, null, null, command.getPhone(), null, null, null, null);
//        if (command.getDriverType() == DriverType.LIMOUSINE) {
//            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
//            driver = new Driver(command.getUserName(), password, salt, EnableStatus.DISABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
//                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
//                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy-MM-dd"), AuthStatus.AUTH_WAIT, travelPic, null, command.getPhone(), null, null);
//        } else if (command.getDriverType() == DriverType.GENERATION) {
//            driver = new Driver(command.getUserName(), password, salt, EnableStatus.DISABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
//                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
//                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy-MM-dd"), AuthStatus.AUTH_WAIT, null, command.getDrivingLicenceType(), command.getPhone(), null, null);
//        } else {
//            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
//            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
//            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
//            driver = new Driver(command.getUserName(), password, salt, EnableStatus.DISABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
//                    command.getName(), null, company, null, 0.0, 0.0, 0.0, 0, false, command.getDriverType(), identityCarPic, drivingLicencePic,
//                    CoreDateUtils.parseDate(command.getStartDriveDate(), "yyyy-MM-dd"), AuthStatus.AUTH_WAIT, travelPic, null, command.getPhone(), businessPic, workPic);
//        }

        driverRepository.save(driver);
        fileUploadService.move(identityCarPic.substring(identityCarPic.lastIndexOf("/") + 1));
//        fileUploadService.move(drivingLicencePic.substring(drivingLicencePic.lastIndexOf("/") + 1));
        if (!CoreStringUtils.isEmpty(command.getTravelPic())) {
            fileUploadService.move(command.getTravelPic().substring(command.getTravelPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getBusinessPic())) {
            fileUploadService.move(command.getBusinessPic().substring(command.getBusinessPic().lastIndexOf("/") + 1));
        }
        if (!CoreStringUtils.isEmpty(command.getWorkPic())) {
            fileUploadService.move(command.getWorkPic().substring(command.getWorkPic().lastIndexOf("/") + 1));
        }
        return driver;
    }

    @Override
    public Driver apiEdit(EditDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setEmail(command.getEmail());
        driver.setName(command.getName());
        driver.setSex(command.getSex());
        driver.setDriverType(command.getDriverType());

        Car car = carService.searchByDriver(driver.getId());
        if (driver.getDriverType() == DriverType.LIMOUSINE) {
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setTravelPic(travelPic);
        } else if (driver.getDriverType() == DriverType.GENERATION) {
            driver.setDrivingLicenceType(command.getDrivingLicenceType());
            if (null != car) {
                carService.delete(car);
            }
        } else {
            String businessPic = command.getBusinessPic().replace("img_tmp", "img");
            String workPic = command.getWorkPic().replaceAll("img_tmp", "img");
            String travelPic = command.getTravelPic().replaceAll("img_tmp", "img");
            driver.setBusinessPic(businessPic);
            driver.setWorkPic(workPic);
            driver.setTravelPic(travelPic);
            if (null != car) {
                car.setCarType(null);
                carService.update(car);
            }
        }

        driverRepository.update(driver);

        return driver;
    }

    @Override
    public Driver apiUpdateHeadPic(UpdateHeadPicCommand command) {
        Driver driver = this.show(command.getId());

        String headPic = command.getHeadPic().replaceAll("img_tmp", "img");
        String oldHeadPic = driver.getHead();
        driver.setHead(headPic);

        driverRepository.update(driver);
        fileUploadService.move(headPic.substring(headPic.lastIndexOf("/") + 1));
        if (null != oldHeadPic) {
            fileUploadService.delete(oldHeadPic.substring(oldHeadPic.lastIndexOf("/") + 1));
        }
        return driver;
    }
}
