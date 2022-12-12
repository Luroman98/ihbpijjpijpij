package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.service.UserServiceImp;

import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("/user")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("messages", userService.loadUserByUsername(principal.getName()).toString());
        return "user/user";
    }

}
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping()
//    public String listUsers(Model model) {
//        // Получим всех людей из Dao и отобразим в пердставление с помощью thymeleaf
//        model.addAttribute("users", userService.listUsers());
//        return "users/listUsers";
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") Long id, Model model) {
//        // Получим 1-го человека из Dao и  передадим на отображение в представление
//        model.addAttribute("user", userService.getUser(id));
//        return "users/getUserById";
//    }
//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "users/new";
//    }
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/users";
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUser((Long)id));
//        return "users/edit";
//    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
//        userService.update(id, user);
//
//        return "redirect:/users";
//    }
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
//}
