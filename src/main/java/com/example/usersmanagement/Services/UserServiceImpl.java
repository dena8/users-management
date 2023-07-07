package com.example.usersmanagement.Services;

import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserResponseDTO;
import com.example.usersmanagement.Models.Entities.UserEntity;
import com.example.usersmanagement.Models.Repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = this.modelMapper.map(userRequestDTO, UserEntity.class);
        UserEntity createdEntity = this.usersRepository.saveAndFlush(userEntity);
        return this.modelMapper.map(createdEntity, UserResponseDTO.class);
    }
}
