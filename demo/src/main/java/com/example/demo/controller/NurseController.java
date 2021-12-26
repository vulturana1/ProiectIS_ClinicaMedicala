package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "nurse/index";
    }

    @PostMapping("/addPatient")
    public void addPatient(@RequestBody User user) {
        nurseService.addPatient(user);
    }

    @DeleteMapping("/deletePatient/{username}")
    public void deletePatient(@PathVariable String username) {
        nurseService.deletePatient(username);
    }

    @GetMapping("/showPatients")
    public ArrayList<String> showPatients() {
        return nurseService.showPatients();
    }

}
