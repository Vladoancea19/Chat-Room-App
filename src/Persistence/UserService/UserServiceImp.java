package Persistence.UserService;

import Persistence.UserDao.UserDao;
import Persistence.UserDao.UserDaoImp;
import Persistence.UserMapper.UserEntityMapper;
import Persistence.UserMapper.UserMapper;
import User.User;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserServiceImp implements UserService {
    private final UserDao userDao = new UserDaoImp();
    private final UserMapper userMapper = new UserEntityMapper();

    @Override
    public User create(User audit) {
        User auditEntity = userMapper.modelToEntity(audit);
        User savedAuditEntity = this.userDao.create(auditEntity);
        return audit;
    }

    @Override
    public List<User> read() {
        List<User> userEntityList = this.userDao.read();
        return userEntityList.stream().map(userMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAllBy(Predicate<User> predicate) {
        return null;
    }

    @Override
    public User update(User audit, Integer id) {
        return null;
    }
}
