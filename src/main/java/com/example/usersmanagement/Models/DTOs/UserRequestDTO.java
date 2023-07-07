package com.example.usersmanagement.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserRequestDTO {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private Date birthDate;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
