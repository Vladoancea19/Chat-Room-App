package Persistence.UserMapper;

import User.User;

public interface UserMapper {
    User entityToModel(User userEntity);

    User modelToEntity(User user);
}
