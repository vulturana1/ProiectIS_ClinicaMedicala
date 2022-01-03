package com.example.demo.model;

import java.text.SimpleDateFormat;

public class Appointment {

    private int id;
    private String usernameDoctor;
    private String usernamePatient;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String date;
    private String time;

    public Appointment(int id, String usernamePatient, String usernameDoctor, String date, String time) {
        this.id = id;
        this.usernameDoctor = usernameDoctor;
        this.usernamePatient = usernamePatient;
        this.date = date;
        this.time = time;
    }

    public Appointment() {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", usernameDoctor='" + usernameDoctor + '\'' +
                ", usernamePatient='" + usernamePatient + '\'' +
                ", date=" + date +
                '}';
    }
}