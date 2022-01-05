package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.lang.System.getProperty;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/index")
    public String index(Model model, Authentication authentication) {
        User user = new User();
        String username = authentication.getName();
        user = patientService.showDetails(username);
        model.addAttribute("user", user);
        return "patient/index";
    }

    @GetMapping("/addAppointment")
    public String addAppointment(Model model){

        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "patient/addAppointment";
    }

    @PostMapping("/addAppointment")
    public String makeAppointment(@ModelAttribute("appointment") Appointment appointment, Model m, BindingResult bindingResult, Authentication authentication) {
        String username = authentication.getName();
        appointment.setUsernamePatient(username);
        if (!bindingResult.hasErrors()) {
            this.patientService.addAppointment(appointment);
            m.addAttribute("message", "Successfully added...");
        }
        return "patient/addAppointment";
    }

    @GetMapping("/showRecipe")
    public String showRecipe(Model model, Authentication authentication){
        String username = authentication.getName();
        ArrayList<Recipe> list = patientService.showRecipe(username);
        model.addAttribute("receipe", list);
        return "patient/showRecipe";
    }

    @GetMapping("/viewDoctors")
    public String viewDoctors(Model model){
        ArrayList<User> list = patientService.showDoctors();
        model.addAttribute("user", list);
        return "patient/viewDoctors";
    }

    @GetMapping("/showAppointments")
    public String showAppointments(Model model, Authentication authentication){
        String username = authentication.getName();
        ArrayList<Appointment> list = patientService.showAppointments(username);
        model.addAttribute("appointment", list);
        return "patient/showAppointments";
    }

}
