package com.turnApp.controllers;

import com.turnApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String userList(Model model) {

        model.addAttribute("user", userService.listAll());

        return "user-list";
    }

    @GetMapping("/form")
    public String createUser() {
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String name, @RequestParam String lastName, @RequestParam Integer age) {
      
        userService.save(name, lastName, age);
        
        return "redirect:/user/list";
    }

}
