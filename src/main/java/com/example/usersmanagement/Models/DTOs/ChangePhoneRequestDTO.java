package com.example.usersmanagement.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class ChangePhoneRequestDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "phone can not be null or blank")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
