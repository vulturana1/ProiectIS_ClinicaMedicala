package com.example.demo.model;

public class Recipe {
    private int id;
    private String usernameDoctor;
    private String usernamePatient;
    private String listOfDrugs;

    public Recipe(int id, String usernameDoctor, String usernamePatient, String listOfDrugs) {
        this.id = id;
        this.usernameDoctor = usernameDoctor;
        this.usernamePatient = usernamePatient;
        this.listOfDrugs = listOfDrugs;
    }

    public Recipe(){

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

    public String getListOfDrugs() {
        return listOfDrugs;
    }

    public void setListOfDrugs(String listOfDrugs) {
        this.listOfDrugs = listOfDrugs;
    }
}