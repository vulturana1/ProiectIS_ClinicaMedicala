package com.example.demo.model;

public class Patient {
    private int idPatient;
    private String patientName;
    private String address;
    private String phoneNumber;
    private String CNP;

    public Patient(int idPatient, String patientName, String address, String phoneNumber, String CNP) {
        this.idPatient = idPatient;
        this.patientName = patientName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CNP = CNP;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
