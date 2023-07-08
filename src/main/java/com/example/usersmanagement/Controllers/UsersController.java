package com.example.usersmanagement.Controllers;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Errors.ErrorResponse;
import com.example.usersmanagement.Models.DTOs.ChangePhoneRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Services.Interfaces.UserService;
import com.example.usersmanagement.Utils.Annotations.ExistId;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(name = "first_name", required = false) String firstName) {
        return ResponseEntity.ok(this.userService.getAll(firstName));
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
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO userRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return new ErrorResponse(errors).getResponse();
        }
        return ResponseEntity.ok(this.userService.createUser(userRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changePhoneNumber(@PathVariable long id, @RequestBody @Valid ChangePhoneRequestDTO changePhoneRequestDTO, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return new ErrorResponse(errors).getResponse();
        }
        return ResponseEntity.ok(this.userService.changePhoneNumber(id, changePhoneRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @ExistId(message = "User not found") Long id) {
        this.userService.deleteBy(id);
        return ResponseEntity.ok().build();
    }

}
