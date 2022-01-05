package com.example.demo.model;

import org.springframework.context.ApplicationEvent;

public class AppointmentEvent extends ApplicationEvent {

    private Appointment appointment;

    public AppointmentEvent(Object source ,Appointment appointment) {
        super(source);
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
