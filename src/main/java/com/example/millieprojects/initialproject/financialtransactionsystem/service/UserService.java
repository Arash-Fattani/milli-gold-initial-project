package com.example.millieprojects.initialproject.financialtransactionsystem.service;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.User;
import com.example.millieprojects.initialproject.financialtransactionsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @SneakyThrows
    @Transactional
    public User updateUser(User user, Integer id) {
        Optional<User> userHolder = userRepository.findById(id);
        if (userHolder.isPresent()) {
            User oldUser = userHolder.get();
            User updatedUser = oldUser.toBuilder()
                    .email(user.getEmail() != null ? user.getEmail() : oldUser.getEmail())
                    .username(user.getUsername() != null ? user.getUsername() : oldUser.getUsername())
                    .build();
            return userRepository.save(updatedUser);

        }
        throw new Exception("User Not Found");
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User findUserById(int id){
        Optional<User> userHolder =  userRepository.findById(id);
        return userHolder.orElse(null);
    }

}
