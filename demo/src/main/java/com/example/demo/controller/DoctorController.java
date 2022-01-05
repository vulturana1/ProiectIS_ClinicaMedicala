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
        return "doctor/index";
    }

    @GetMapping("/addPatient")
    public String addPatient(Model model) {

        User user = new User();
        model.addAttribute("user", user);
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
        return "doctor/addPatient";
    }

    @GetMapping("/addNurse")
    public String addNurse(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "doctor/addNurse";
    }

    @PostMapping("/addNurse")
    public String submitAddNurse(@ModelAttribute("user") User user, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            if (this.doctorService.addNurse(user) == true) {
                m.addAttribute("message", "Successfully added...");
            } else {
                m.addAttribute("message", "An account with the same email already exists...");
            }
        }
        return "doctor/addNurse";
    }

    @DeleteMapping("/deleteNurse/{username}")
    public String deleteNurse(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        doctorService.deleteNurse(username);
        ArrayList<User> list = doctorService.showPatients();
        model.addAttribute("user", list);
        return "redirect:/doctor/showNurses";
    }

    @GetMapping("/addRecipe")
    public String addRecipe(Model model){
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "doctor/addRecipe";
    }

    @PostMapping("/addRecipe")
    public String submitAddRecipe(@ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model m, Authentication authentication) {
        if (!bindingResult.hasErrors()) {
            String username = authentication.getName();
            recipe.setUsernameDoctor(username);
            this.doctorService.addRecipe(recipe);
            m.addAttribute("message", "Successfully added...");
        }
        return "doctor/addRecipe";
    }

    @GetMapping("/deletePatient/{username}")
    public String deletePatient(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        doctorService.deletePatient(username);
        ArrayList<User> list = doctorService.showPatients();
        model.addAttribute("user", list);
        return "redirect:/doctor/showPatients";
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

    @RequestMapping("/showRecipes")
    public String showRecipes(Model model, Authentication authentication) {
        String username = authentication.getName();
        ArrayList<Recipe> list = doctorService.showRecipes(username);
        model.addAttribute("recipe", list);
        return "doctor/showRecipes";
    }

    @GetMapping("/updateRecipe")
    public String updateRecipe(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/updateRecipe";
    }

    @PostMapping("/updateRecipe")
    public String submitUpdateRecipe(@ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model m) {
        if (!bindingResult.hasErrors()) {
            this.doctorService.updateRecipe(recipe.getId(),recipe.getListOfDrugs());
            m.addAttribute("message", "Successfully updated...");
        }
        m.addAttribute("mapsApiKey", getProperty("mapsKey"));
        return "doctor/updateRecipe";
    }

    @GetMapping("/viewMessages")
    public String notifyDoctor(Model model, Authentication authentication) {
        String username = authentication.getName();
        ArrayList<String> list = doctorService.notifyDoctor(username);
        model.addAttribute("mesaj", list);
        return "doctor/viewMessages";
    }

}
