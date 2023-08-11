package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserCreateDto userDto);

    UserDto updateUser(UserUpdateDto userDto);

    UserDto findUserById(Long id);

    List<UserDto> findAllUsers();

    void deleteUserById(Long id);

}
