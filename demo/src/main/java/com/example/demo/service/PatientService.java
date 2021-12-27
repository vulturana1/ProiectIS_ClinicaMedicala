package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.NurseRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ArrayList<Recipe> showRecipe(String usernamePatient) {
        ResultSet rs = patientRepository.showRecipe(usernamePatient);
        ArrayList<Recipe> showR = new ArrayList<>();
        try {
            while (rs.next()) {

                String id = rs.getString("id");
                String usernameD = rs.getString("usernameDoctor");
                String usernameP = rs.getString("usernamePatient");
                String listOfDrugs = rs.getString("listOfDrugs");

                Recipe recipe = new Recipe(Integer.valueOf(id), usernameD, usernameP, listOfDrugs);
                String s = id + "  " + usernameD + " " + usernameP + " --- Recipe: " + listOfDrugs;
                showR.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showR;
    }

    public void addAppointment(Appointment appointment) {
        patientRepository.addAppointment(appointment);
    }

    public User showDetails(String username) {
        ResultSet rs = patientRepository.showDetails(username);
        User user = new User();
        try {
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                user.setUsername(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhoneNumber(phoneNumber);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> showDoctors() {
        ResultSet rs = patientRepository.showDoctors();
        ArrayList<User> showP = new ArrayList<>();
        try {
            while (rs.next()) {

                String id = rs.getString("id");
                String nume = rs.getString("firstName");
                String prenume = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");

                User user = new User(Integer.valueOf(id),nume,prenume,username,password,role,phoneNumber);
                String s = id + "  " + nume + " " + prenume + " " + username+" "+ password + " " + role + " " + phoneNumber;
                showP.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showP;
    }

}