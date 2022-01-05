package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService{

    PatientRepository patientRepository;

    @Override
    public void sendAppointmentNotification(Appointment appointment) {
        System.out.println("mesaj trimis");
        //patientRepository.insertDoctorNotify(appointment);
    }
}
