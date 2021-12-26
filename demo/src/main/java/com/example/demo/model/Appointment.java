package com.example.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Appointment {

    private int id;
    private String usernameDoctor;
    private String usernamePatient;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;

    public Appointment(int id, String usernamePatient, String usernameDoctor, String date) {
        this.id = id;
        this.usernameDoctor = usernameDoctor;
        this.usernamePatient = usernamePatient;
        try {
            this.date = dt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernameDoctor() {
        return usernameDoctor;
    }

    public void setUsernameDoctor(String usernameDoctor) {
        this.usernameDoctor = usernameDoctor;
    }

    public String getUsernamePatient() {
        return usernamePatient;
    }

    public void setUsernamePatient(String usernamePatient) {
        this.usernamePatient = usernamePatient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", usernameDoctor='" + usernameDoctor + '\'' +
                ", usernamePatient='" + usernamePatient + '\'' +
                ", date=" + dt.format(date) +
                '}';
    }
}