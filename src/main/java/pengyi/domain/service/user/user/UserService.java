package pengyi.domain.service.user.user;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.BaseListUserCommand;
import pengyi.application.user.user.command.RegisterUserCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.core.upload.IFileUploadService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.user.IUserRepository;
import pengyi.domain.model.user.user.User;
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
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<User, String> userRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<User> pagination(BaseListUserCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return userRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, orderList);
    }

    @Override
    public User edit(EditUserCommand command) {
        User user = this.show(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());

        user.setEmail(command.getEmail());
        user.setSex(command.getSex());
        user.setName(command.getName());

        userRepository.update(user);
        return user;
    }

    @Override
    public User show(String id) {
        User user = userRepository.getById(id);
        if (null == user) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return user;
    }

    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateReportCount(String id) {
        User user = this.show(id);
        user.setReportCount(user.getReportCount() + 1);
        userRepository.update(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void addLock() {
        userRepository.addLock();
    }

    @Override
    public User apiUpdateHeadPic(UpdateHeadPicCommand command) {
        User user = this.show(command.getId());
        String headPic = command.getHeadPic().replaceAll("img_tmp", "img");
        String oldHeadPic = user.getHead();
        user.setHead(headPic);

        userRepository.update(user);
        fileUploadService.move(headPic.substring(headPic.lastIndexOf("/") + 1));
        if (null != oldHeadPic) {
            fileUploadService.delete(oldHeadPic.substring(oldHeadPic.lastIndexOf("/") + 1));
        }
        return user;
    }

    @Override
    public User apiRegister(RegisterUserCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        Role role = roleService.searchByName("user");
        User user = new User(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.USER,
                null, null, null, 0, 0);

        userRepository.save(user);
        return user;
    }

}
