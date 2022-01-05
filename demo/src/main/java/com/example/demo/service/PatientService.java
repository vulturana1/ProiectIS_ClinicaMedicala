package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.AppointmentEvent;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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
        patientRepository.insertDoctorNotify(appointment);
        applicationEventPublisher.publishEvent(new AppointmentEvent(this, appointment));
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

    public ArrayList<Appointment> showAppointments(String usernamePatient) {
        ResultSet rs = patientRepository.showAppointments(usernamePatient);
        ArrayList<Appointment> showA = new ArrayList<>();
        try {
            while (rs.next()) {

                String id = rs.getString("id");
                String usernameD = rs.getString("usernameDoctor");
                String usernameP = rs.getString("usernamePatient");
                String date = rs.getString("date");
                String time = rs.getString("time");

                Appointment appointment = new Appointment(Integer.valueOf(id), usernameP, usernameD, date, time);
                showA.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showA;
    }

}