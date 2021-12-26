package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class NurseService {
    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }
    public void addPatient(User user) {
        nurseRepository.addPatient(user);
    }

    public void deletePatient(String username) {
        nurseRepository.deletePatient(username);
    }

    public ArrayList<String> showPatients() {
        ResultSet rs = nurseRepository.showPatients();
        ArrayList<String> showP = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String nume = rs.getString("firstName");
                String prenume = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");

                String s = id + "  " + nume + " " + prenume + " " + username+" "+ password + " " + role + " " + phoneNumber;
                showP.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showP;
    }
}