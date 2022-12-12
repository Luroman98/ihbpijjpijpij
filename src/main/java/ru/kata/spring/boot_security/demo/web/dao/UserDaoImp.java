package ru.kata.spring.boot_security.demo.web.dao;

import com.mysql.cj.xdevapi.Session;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public List<User> listUsers() {
        return entityManager.createQuery("select w from User w", User.class).getResultList();
    }
    @Transactional
    public void update(Long id, User updateUser) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(updateUser.getFirstName());
        userToBeUpdated.setLastName(updateUser.getLastName());
        userToBeUpdated.setEmail(updateUser.getEmail());

    }
    @Transactional
    public void deleteUser(Long id) {
        System.out.println(getUser(id));
        entityManager.remove(getUser(id));

    }
    @Transactional
    public User getUserByUserName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> itemRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("firstName"), name));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
