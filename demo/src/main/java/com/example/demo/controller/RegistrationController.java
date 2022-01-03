package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static java.lang.System.getProperty;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("user") User user, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            if (this.userService.addUser(user) == true) {
                m.addAttribute("message", "Successfully added...");
            } else {
                m.addAttribute("message", "An account with the same email already exists...");
            }
        }
        return "register";
    }
}
