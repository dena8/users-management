package com.example.usersmanagement.Controllers;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Errors.ErrorResponse;
import com.example.usersmanagement.Models.DTOs.ChangePhoneRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.Entities.UserEntity;
import com.example.usersmanagement.Services.Interfaces.UserService;
import com.example.usersmanagement.Utils.Annotations.ExistId;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.Operation;

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
    @Operation(summary = "Get all users", description = "Get all users by first name optional and ordered by last name and birth date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully getted all users by optional criteria first name and ordered by last name and birth date",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))})
    })
    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(name = "first_name", required = false) String firstName) {
        return ResponseEntity.ok(this.userService.getAll(firstName));
    }


    @Operation(summary = "Get user by id", description = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully return user information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when id not exist ApiException is thrown -User not found! ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.userService.getById(id));
        } catch (ApiException e) {
            return new ErrorResponse(e).getResponse();
        }
    }

    @Operation(summary = "Create user", description = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully return created user information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when first name is null or blank ApiException is thrown - first name can not be null or blank! ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when last name is null or blank ApiException is thrown - last name can not be null or blank! ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when email is not valid exception is thrown - email must be valid!",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO userRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return new ErrorResponse(errors).getResponse();
        }
        return ResponseEntity.ok(this.userService.createUser(userRequestDTO));
    }

    @Operation(summary = "Update user phone number", description = "Update user phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user phone number and return user information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when id not exist ApiException is thrown - User not found! ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> changePhoneNumber(@PathVariable long id, @RequestBody @Valid ChangePhoneRequestDTO changePhoneRequestDTO, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return new ErrorResponse(errors).getResponse();
        }
        return ResponseEntity.ok(this.userService.changePhoneNumber(id, changePhoneRequestDTO));
    }
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
            @ApiResponse(responseCode = "404", description = "when id not exist ApiException is thrown - User not found! ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @ExistId(message = "User not found") Long id) {
        this.userService.deleteBy(id);
        return ResponseEntity.ok().build();
    }

}
