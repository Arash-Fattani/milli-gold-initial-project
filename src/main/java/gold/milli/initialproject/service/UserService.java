package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User createUser(User user);

    Page<User> fetchAllUsers(int page, int size, String sortBy, String direction);

    User updateUser(User user, Integer userId) throws Exception;

    void deleteUser(int userId);

    User findUserById(int userId);
}
