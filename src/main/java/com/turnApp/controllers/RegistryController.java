package com.turnApp.controllers;

import com.turnApp.Exceptions.WebExceptions;
import com.turnApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registry")
public class RegistryController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String registry() {
        return "registry";
    }

    @PostMapping("")
    public String saveRegistry(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String password2) {

        try {
            userService.save(username, password, password2);
            return "redirect:/";
        } catch (WebExceptions ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("username", username);
            return "registry";
        }

    }

}
