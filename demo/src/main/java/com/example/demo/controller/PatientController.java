package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "patient/index";
    }

    @PostMapping("/addAppointment")
    public void addAppointment(@RequestBody Appointment appointment){
        patientService.addAppointment(appointment);
    }

    @GetMapping("/showRecipe/{usernamePatient}")
    public ArrayList<String> showRecipe(@PathVariable String usernamePatient){
        return patientService.showRecipe(usernamePatient);
    }

    @GetMapping("/showDetails/{usernamePatient}")
    public ArrayList<String> showDetails(@PathVariable String usernamePatient) {
        return patientService.showDetails(usernamePatient);
    }

}
