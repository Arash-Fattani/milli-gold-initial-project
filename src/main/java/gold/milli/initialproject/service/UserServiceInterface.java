package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.User;

import java.util.List;

public interface UserServiceInterface {
    User createUser(User user);

    List<User> fetchAllUsers();

    User updateUser(User user, Integer id);

    void deleteUser(int id);

    User findUserById(int Id);
}
