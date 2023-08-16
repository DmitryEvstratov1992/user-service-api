package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.exception.BusinessException;
import com.citizen.userserviceapi.mapper.UserMapper;
import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserUpdateDto;
import com.citizen.userserviceapi.model.entity.User;
import com.citizen.userserviceapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.citizen.userserviceapi.model.ExceptionCodes.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private DogService dogService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void when_call_createUser_then_return_result() {

        when(userRepository.save(any())).thenReturn(new User());

        userService.createUser(new UserCreateDto()
                .setFirstName("Ivan")
                .setLastName("Dubov"));

        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).toUser(any());
        verify(userMapper, times(1)).toUserDto(any());

    }

    @Test
    public void when_call_updateUser_without_user_then_throw_exception() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        try {
            userService.updateUser(new UserUpdateDto().setId(2L));
        } catch (BusinessException e) {
            assertEquals(USER_NOT_FOUND.getDescription(), e.getMessage());
        }

    }

    @Test
    public void when_call_updateUser_then_return_result() {
        when(userRepository.save(any())).thenReturn(new User());
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        userService.updateUser(new UserUpdateDto()
                .setId(2L));

        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).toUserDto(any());
    }

    @Test
    public void when_call_findUserById_without_user_then_throw_exception() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        try {
            userService.findUserById(2L);
        } catch (BusinessException e) {
            assertEquals(USER_NOT_FOUND.getDescription(), e.getMessage());
        }

    }

    @Test
    public void when_call_findUserById_then_return_result() {

        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        userService.findUserById(2L);

        verify(userMapper, times(1)).toUserDto(any());

    }

    @Test
    public void when_call_findAllUsers_then_return_result() {

        when(userRepository.findAll()).thenReturn(List.of(
                new User(),
                new User()));

        userService.findAllUsers();

        verify(userMapper, times(2)).toUserDto(any());

    }

    @Test
    public void when_call_deleteUserById_without_user_then_throw_exception() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        try {
            userService.deleteUserById(2L);
        } catch (BusinessException e) {
            assertEquals(USER_NOT_FOUND.getDescription(), e.getMessage());
        }

    }

    @Test
    public void when_call_deleteUserById_then_return_result() {

        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        userService.deleteUserById(2L);

    }

}
