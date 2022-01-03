package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.lang.System.getProperty;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @RequestMapping("/index")
    public String index(Model model, Authentication authentication) {
        User user = new User();
        String username = authentication.getName();
        user = nurseService.findNurse(username);
        model.addAttribute("user", user);
        model.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "nurse/index";
    }

    @GetMapping("/addPatient")
    public String addPatient(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "nurse/addPatient";
    }

    @PostMapping("/addPatient")
    public String submitAddPatient(@ModelAttribute("user") User user, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            if (this.nurseService.addPatient(user) == true) {
                m.addAttribute("message", "Successfully added...");
            } else {
                m.addAttribute("message", "An account with the same email already exists...");
            }
        }
        return "nurse/addPatient";
    }

    @GetMapping("/deletePatient/{username}")
    public String deletePatient(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        nurseService.deletePatient(username);
        ArrayList<User> list = nurseService.showPatients();
        model.addAttribute("user", list);
        return "redirect:/nurser/showPatients";
    }

    @RequestMapping("/showPatients")
    public String showPatients(Model model) {
        ArrayList<User> list = nurseService.showPatients();
        model.addAttribute("user", list);
        return "nurse/showPatients";
    }

}
