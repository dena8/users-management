package com.example.usersmanagement.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePhoneRequestDTO {
    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
