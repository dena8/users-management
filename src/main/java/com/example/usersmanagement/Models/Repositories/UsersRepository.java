package com.example.usersmanagement.Models.Repositories;

import com.example.usersmanagement.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
}
