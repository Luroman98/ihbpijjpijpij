package ru.kata.spring.boot_security.demo.web.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUser(Long id);
    void update(Long id, User updateUser);
    void deleteUser(Long id);

    User getUserByUserName(String name);
}