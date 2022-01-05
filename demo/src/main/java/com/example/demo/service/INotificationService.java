package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.User;

public interface INotificationService {

    void sendAppointmentNotification(Appointment appointment);
}
