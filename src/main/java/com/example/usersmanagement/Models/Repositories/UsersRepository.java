package com.example.usersmanagement.Models.Repositories;

import com.example.usersmanagement.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity,Long> {
}
