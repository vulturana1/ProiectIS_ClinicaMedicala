package com.example.demo.service;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public boolean addPatient(User user) {
        return doctorRepository.addPatient(user);
    }

    public void addRecipe(Recipe recipe) {
        doctorRepository.addRecipe(recipe);
    }

    public void updateRecipe(int id, String listOfDrugs) {
        doctorRepository.updateRecipe(id, listOfDrugs);
    }

    public void deletePatient(String username) {
        doctorRepository.deletePatient(username);
    }

    public ArrayList<User> showPatients() {
        ResultSet rs = doctorRepository.showPatients();
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

    public ArrayList<User> showNurses() {
        ResultSet rs = doctorRepository.showNurses();
        ArrayList<User> showN = new ArrayList<>();
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
                showN.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showN;
    }

    public User findDoctor(String username) {
        ResultSet rs = doctorRepository.findDoctor(username);
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