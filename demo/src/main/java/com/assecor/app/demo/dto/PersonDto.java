package com.assecor.app.demo.dto;

public class PersonDto {
    private final long id;
    private String lastname;
    private String firstname;
    private String address;
    private String color;

    public PersonDto(long id, String lastname, String firstname, String address, String color) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.color = color;
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

    public String getColor() {
        return color;
    }
}
