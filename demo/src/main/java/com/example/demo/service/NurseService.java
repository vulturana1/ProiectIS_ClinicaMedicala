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
    public boolean addPatient(User user) {
        return nurseRepository.addPatient(user);
    }

    public void deletePatient(String username) {
        nurseRepository.deletePatient(username);
    }

    public ArrayList<User> showPatients() {
        ResultSet rs = nurseRepository.showPatients();
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

    public User findNurse(String username) {
        ResultSet rs = nurseRepository.findNurse(username);
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
}