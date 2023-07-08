package com.example.usersmanagement.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class UserRequestDTO {
    @JsonProperty("first_name")
    @NotBlank(message = "first name can not be null or blank")
    private String firstName;
    @JsonProperty("last_name")
    @NotBlank(message = "last name can not be null or blank")
    private String lastName;
    @JsonProperty("birth_date")
    private Date birthDate;
    @JsonProperty("phone_number")
    @NotBlank(message = "phone can not be null or blank")
    private String phoneNumber;
    @NotBlank(message = "email can not be null or blank")
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
