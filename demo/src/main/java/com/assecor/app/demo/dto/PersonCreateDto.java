package com.assecor.app.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class PersonCreateDto {
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;
    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Color cannot be empty")
    private String color;

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
