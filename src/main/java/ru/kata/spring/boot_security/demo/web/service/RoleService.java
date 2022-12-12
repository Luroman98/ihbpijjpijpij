package ru.kata.spring.boot_security.demo.web.service;

import ru.kata.spring.boot_security.demo.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public void saveRole(Role user);

    public Set<Role> getRole(List<Long> id);

    public List<Role> listAll();
}
