package ru.kata.spring.boot_security.demo.web.service;

import ru.kata.spring.boot_security.demo.web.model.User;


import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    List<User> listUsers();
    Object getUser(Long id);


    void updateUser(Long id, User user);

    void deleteUser(Long id);
//    boolean saveUser(User user);
}
