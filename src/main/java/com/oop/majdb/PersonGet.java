package com.oop.majdb;

public class PersonGet {
    //ALl except posts of a person
    private int userID;
    private String name;
    private String email;

    public PersonGet(int userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}