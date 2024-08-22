package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(User user, Integer userId) throws Exception{
        Optional<User> userHolder = userRepository.findById(userId);
        if (userHolder.isPresent()) {
            User oldUser = userHolder.get();
            oldUser.setEmail(user.getEmail() != null ? user.getEmail() : oldUser.getEmail());
            oldUser.setUsername(user.getUsername() != null ? user.getUsername() : oldUser.getUsername());
            return userRepository.save(oldUser);

        }
        throw new Exception("User Not Found");
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(int userId){
        Optional<User> userHolder =  userRepository.findById(userId);
        return userHolder.orElse(null);
    }

}
