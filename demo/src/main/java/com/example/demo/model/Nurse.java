package com.example.demo.model;

public class Nurse {

    private int idNurse;
    private String nurseName;
    private String department;
    private String phoneNumber;

    public Nurse(int idNurse, String nurseName, String department, String phoneNumber) {
        this.idNurse = idNurse;
        this.nurseName = nurseName;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    public int getIdNurse() {
        return idNurse;
    }

    public void setIdNurse(int idNurse) {
        this.idNurse = idNurse;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
