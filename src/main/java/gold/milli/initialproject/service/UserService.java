package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> fetchAllUsers();

    User updateUser(User user, Integer userId);

    void deleteUser(int userId);

    User findUserById(int userId);
}
