package com.example.usersmanagement.Services.Interfaces;

import com.example.usersmanagement.Models.Entities.UserEntity;

import java.util.List;

public interface UserCriteriaService {
    List<UserEntity> findOrderUsers(String firstName);
}
