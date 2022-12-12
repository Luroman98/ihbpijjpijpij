package ru.kata.spring.boot_security.demo.web.dao;

import ru.kata.spring.boot_security.demo.web.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    Role getRole(Long id);
    List<Role> listAllRole();
}