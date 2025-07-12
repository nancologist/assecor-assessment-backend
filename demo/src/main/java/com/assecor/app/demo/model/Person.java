package com.assecor.app.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Person {
    private long id;
    private String lastname;
    private String firstname;
    private String address;
    private int colorId;

    public Person() {}

    public Person(long id, String lastname, String firstname, String address, int colorId) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.colorId = colorId;
    }

    public long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public int getColorId() {
        return colorId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
