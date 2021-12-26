package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.lang.System.getProperty;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping("/index")
    public String index(Model model, Authentication authentication) {
        User user = new User();
        String username = authentication.getName();
        user = doctorService.findDoctor(username);
        model.addAttribute("user", user);
        model.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/index";
    }

    @GetMapping("/addPatient")
    public String addPatient(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/addPatient";
    }

    @PostMapping("/addPatient")
    public String submitAddPatient(@ModelAttribute("user") User user, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            if (this.doctorService.addPatient(user) == true) {
                m.addAttribute("message", "Successfully added...");
            } else {
                m.addAttribute("message", "An account with the same email already exists...");
            }
        }
        m.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/addPatient";
    }

    @GetMapping("/addRecipe")
    public String addRecipe(Model model) {

        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/addRecipe";
    }

    @PostMapping("/addRecipe")
    public String submitAddRecipe(@ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            this.doctorService.addRecipe(recipe);
            m.addAttribute("message", "Successfully added...");
        }
        m.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/addRecipe";
    }

    @PutMapping("/updateRecipe/{id}")
    public void updateRecipe(@RequestBody String listOfDrugs, @PathVariable int id) {
        doctorService.updateRecipe(id, listOfDrugs);
    }

    @DeleteMapping("/deletePatient/{username}")
    public void deletePatient(@PathVariable String username) {
        doctorService.deletePatient(username);
    }

    @RequestMapping("/showPatients")
    public String showPatients(Model model) {
        ArrayList<User> list = doctorService.showPatients();
        model.addAttribute("user", list);
        return "doctor/showPatients";
    }

    @RequestMapping("/showNurses")
    public String showNurses(Model model) {
        ArrayList<User> list = doctorService.showNurses();
        model.addAttribute("user", list);
        return "doctor/showNurses";
    }

}
