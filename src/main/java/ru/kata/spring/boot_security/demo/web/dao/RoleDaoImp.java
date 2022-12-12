package ru.kata.spring.boot_security.demo.web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Role role) {
        entityManager.merge(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> listAllRole() {
        return entityManager.createQuery("select w from Role w", Role.class).getResultList();
    }

}
