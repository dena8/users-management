package com.example.usersmanagement.Services;

import com.example.usersmanagement.Errors.ApiException;
import com.example.usersmanagement.Models.DTOs.ChangePhoneRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserRequestDTO;
import com.example.usersmanagement.Models.DTOs.UserResponseDTO;
import com.example.usersmanagement.Models.Entities.UserEntity;
import com.example.usersmanagement.Models.Repositories.UsersRepository;
import com.example.usersmanagement.Services.Interfaces.UserCriteriaService;
import com.example.usersmanagement.Services.Interfaces.UserService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.usersmanagement.Utils.Constants.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UserCriteriaService userCriteriaService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UsersRepository usersRepository, UserCriteriaService userCriteriaService, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.userCriteriaService = userCriteriaService;
        this.modelMapper = modelMapper;
    }

    @Transactional
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

    @Override
    public List<UserResponseDTO> getAll(String firstName) {
        List<UserEntity> users = this.userCriteriaService.findOrderUsers(firstName);
        return modelMapper.map(users, new TypeToken<List<UserResponseDTO>>() {
        }.getType());
    }

    @Transactional
    @Override
    public UserResponseDTO changePhoneNumber(long id, ChangePhoneRequestDTO changePhoneRequestDTO) throws ApiException {
        UserEntity user = getUserOrThrowEx(id);
        UserEntity userEntity = user.setPhoneNumber(changePhoneRequestDTO.getPhoneNumber());

        return this.modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Transactional
    @Override
    public void deleteBy(long id) {
        this.usersRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return this.usersRepository.existsById(id);
    }

    private UserEntity getUserOrThrowEx(long id) throws ApiException {
        return this.usersRepository.findById(id).orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }

}
