package com.example.usersmanagement.Services;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getById(long id) throws ApiException;
}
