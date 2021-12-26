package com.example.demo.service;

import com.example.demo.model.Appointment;
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

    public ArrayList<String> showRecipe(String usernamePatient) {
        ResultSet rs = patientRepository.showRecipe(usernamePatient);
        ArrayList<String> showR = new ArrayList<>();
        try {
            while (rs.next()) {

                String id = rs.getString("id");
                String usernameD = rs.getString("usernameDoctor");
                String usernameP = rs.getString("usernamePatient");
                String listOfDrugs = rs.getString("listOfDrugs");

                String s = id + "  " + usernameD + " " + usernameP + " --- Recipe: " + listOfDrugs;
                showR.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showR;
    }

    public void addAppointment(Appointment appointment) {
        patientRepository.addAppointment(appointment);
    }

    public ArrayList<String> showDetails(String usernamePatient) {
        ResultSet rs = patientRepository.showDetails(usernamePatient);
        ArrayList<String> showD = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String nume = rs.getString("firstName");
                String prenume = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");

                String s = id + "  " + nume + " " + prenume + " " + username + " " + password + " " + role + " " + phoneNumber;
                showD.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showD;
    }
}