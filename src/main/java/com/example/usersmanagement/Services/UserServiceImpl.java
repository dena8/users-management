package com.example.usersmanagement.Services;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserResponseDTO;
import com.example.usersmanagement.Models.Entities.UserEntity;
import com.example.usersmanagement.Models.Repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.example.usersmanagement.Utils.Constants.USER_NOT_FOUND;

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

    @Override
    public UserResponseDTO getById(long id) throws ApiException {
        UserEntity user = getUserOrThrowEx(id);
        return this.modelMapper.map(user, UserResponseDTO.class);
    }

    private UserEntity getUserOrThrowEx(long id) throws ApiException {
        return this.usersRepository.findById(id).orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }
}
