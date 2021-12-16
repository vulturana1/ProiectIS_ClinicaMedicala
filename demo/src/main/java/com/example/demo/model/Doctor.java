package com.example.demo.model;

public class Doctor {

    private int idDoctor;
    private String doctorName;
    private String department;
    private int cabinetNumber;
    private String phoneNumber;

    public Doctor(int idDoctor, String doctorName, String department, int cabinetNumber, String phoneNumber) {
        this.idDoctor = idDoctor;
        this.doctorName = doctorName;
        this.department = department;
        this.cabinetNumber = cabinetNumber;
        this.phoneNumber = phoneNumber;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public String getdoctorName() {
        return doctorName;
    }

    public String getDepartment() {
        return department;
    }

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public void setdoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
