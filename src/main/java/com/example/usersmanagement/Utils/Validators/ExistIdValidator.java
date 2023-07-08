package com.example.usersmanagement.Utils.Validators;

import com.example.usersmanagement.Services.Interfaces.UserService;
import com.example.usersmanagement.Utils.Annotations.ExistId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistId, Long> {
    private final UserService userService;

    public ExistIdValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ExistId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return this.userService.isExist(id);
    }
}
