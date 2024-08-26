package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User createUser(CreateUserRequestDto user);

    Page<User> fetchAllUsers(Integer page, Integer size, String sortBy, String direction);

    User updateUser(UpdateUserRequestDto user, Integer userId) throws Exception;

    void deleteUser(Integer userId);

    User findUserById(Integer userId) throws Exception;
}
