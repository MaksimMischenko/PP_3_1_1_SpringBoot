package com.example.pp_3_1_1_springboot.controller;

import com.example.pp_3_1_1_springboot.UserService.UserService;
import com.example.pp_3_1_1_springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "listUsers";
    }

    @GetMapping("/addUser")
    public String createUserFrom(User user) {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserFrom(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

}
