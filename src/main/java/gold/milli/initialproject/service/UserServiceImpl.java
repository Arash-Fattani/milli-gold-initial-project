package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(CreateUserRequestDto user)
    {
        User newUser = new User(user.getUsername(), user.getPassword(), user.getRoles(), user.getEmail());
        return userRepository.save(newUser);
    }

    @Override
    public Page<User> fetchAllUsers(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User updateUser(UpdateUserRequestDto user, Integer id) throws Exception{
            User oldUser = findUserById(id);
            oldUser.setEmail(user.getEmail() != null ? user.getEmail() : oldUser.getEmail());
            oldUser.setUsername(user.getUsername() != null ? user.getUsername() : oldUser.getUsername());
            return userRepository.save(oldUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(int id) throws Exception {
        Optional<User> userHolder =  userRepository.findById(id);
        if (userHolder.isPresent())
            return userHolder.get();
        else
            throw new Exception("User not found");
    }

}
