package Persistence.UserMapper;

import User.User;

public class UserEntityMapper implements UserMapper {
    @Override
    public User entityToModel(User userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    @Override
    public User modelToEntity(User user) {
        User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
