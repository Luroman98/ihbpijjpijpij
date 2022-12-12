package ru.kata.spring.boot_security.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.dao.UserDao;
import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class UserServiceImp implements  UserDetailsService, UserService {


   private final UserDao userDao;

   private final BCryptPasswordEncoder bCryptPasswordEncoder;
   @Autowired
   @Lazy
   public UserServiceImp(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userDao = userDao;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Transactional
    @Override
   public boolean saveUser(User user) {
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         userDao.add(user);
         return true;
//      }
   }

   @Override
   public List<User> listUsers() {
      return (List<User>) userDao.listUsers();
   }

   @Override
   public Object getUser(Long id) {
      return userDao.getUser(id);
   }
   @Override
   public void updateUser(Long id, User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userDao.update(id, user);
   }
   public void deleteUser(Long id) {
      userDao.deleteUser(id);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userDao.getUserByUserName(username);
      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }
      return user;
   }
}
