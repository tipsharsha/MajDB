package com.oop.majdb.Response;

public class PersonGet {

    private String name;
    private int userID;
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
