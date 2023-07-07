package com.example.usersmanagement.Controllers;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Errors.ErrorResponse;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Services.UserService;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.userService.getById(id));
        } catch (ApiException e) {
            return new ErrorResponse(e).getResponse();
        }
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(this.userService.createUser(userRequestDTO));
    }
}
