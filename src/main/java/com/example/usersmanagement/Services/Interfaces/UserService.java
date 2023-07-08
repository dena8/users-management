package com.example.usersmanagement.Services.Interfaces;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getById(long id) throws ApiException;

    List<UserResponseDTO> getAll(String firstName);
}
