package com.example.demo.model;

import com.example.demo.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SendDoctorNotify {

    @Autowired
    private INotificationService notificationService;

    @EventListener
    public void handleNewUserEvent(final AppointmentEvent appointmentEvent) {

        notificationService.sendAppointmentNotification(appointmentEvent.getAppointment());

    }
}
