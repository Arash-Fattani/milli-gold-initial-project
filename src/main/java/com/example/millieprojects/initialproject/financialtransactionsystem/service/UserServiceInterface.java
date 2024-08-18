package com.example.millieprojects.initialproject.financialtransactionsystem.service;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);

    List<User> getUsers();

    User updateUser(User user, Integer id);

    void deleteUser(int id);

    User findUserById(int Id);
}
