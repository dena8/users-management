package com.example.usersmanagement.Utils;

import com.example.usersmanagement.Errors.ErrorMessage;

public class Constants {
    public static final ErrorMessage USER_NOT_FOUND = new ErrorMessage(404, "User not found!");
    public static final ErrorMessage GLOBAL_ERROR_MESSAGE = new ErrorMessage(422, "Something went wrong, please try again");
}
