package com.turnApp.controllers;

import com.turnApp.entities.User;
import com.turnApp.services.UserService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createUser(Model model,@RequestParam(required = false) String id) {
        if (id != null) {
            Optional<User> optional = userService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("user", optional.get());
            } else {
                return "redirect:/user/list";
            }
        } else {
            model.addAttribute("user", new User());
        }
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(Model model, RedirectAttributes redirect, @ModelAttribute User user) {
        try {
            userService.save(user);
            redirect.addFlashAttribute("succes", "¡Usuario creado con exito!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(required = true) String id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }

}
