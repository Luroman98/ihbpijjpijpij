package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.RoleService;

import ru.kata.spring.boot_security.demo.web.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/printUsers";
    }

    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("roles", roleService.listAll());
        return "admin/newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("idRoles") List<Long> idRoles) {
        user.setRoles(roleService.getRole(idRoles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.listAll());
        return "admin/updateUser";
    }

    @PatchMapping("/")
    public String updateUser(@ModelAttribute("user") User user, Long id, @RequestParam("idRoles") List<Long> idRoles) {
        user.setRoles(roleService.getRole(idRoles));
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
}