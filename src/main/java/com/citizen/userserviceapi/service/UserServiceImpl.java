package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.exception.BusinessException;
import com.citizen.userserviceapi.mapper.UserMapper;
import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.dto.UserUpdateDto;
import com.citizen.userserviceapi.model.entity.User;
import com.citizen.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.citizen.userserviceapi.model.ExceptionCodes.USER_NOT_FOUND;

@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "users")
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @CachePut(key = "#userDto.firstName")
    @Transactional
    @Override
    public UserDto createUser(UserCreateDto userDto) {
        log.info("Start method createUser {}", userDto);

        User user = userRepository.save(userMapper.toUser(userDto));

        log.info("Saved user with id = {}", user.getId());

        return userMapper.toUserDto(user);
    }

    @CachePut(key = "#userDto.id")
    @Transactional
    @Override
    public UserDto updateUser(UserUpdateDto userDto) {
        log.info("Start method updateUser {}", userDto);

        Optional<User> user = getUser(userDto.getId());

        User us = user.get();
        us.setFirstName(userDto.getFirstName());
        us.setLastName(userDto.getLastName());
        us.setDogIds(userDto.getDogIds());
        us.setOrganizationId(userDto.getOrganizationId());

        log.info("Updated user with id = {}", us.getId());

        userRepository.save(us);

        return userMapper.toUserDto(us);
    }

    @Cacheable(key = "#id")
    @Override
    public UserDto findUserById(Long id) {
        log.info("Start method findUserById id = {}", id);

        Optional<User> user = getUser(id);
        User us = user.get();

        return userMapper.toUserDto(us);
    }

    @Cacheable
    @Override
    public List<UserDto> findAllUsers() {
        log.info("Start method findAllUsers");

        List<UserDto> dtos = userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

        log.info("Found {} users", dtos.size());

        return dtos;
    }

    @CacheEvict(key = "#id")
    @Transactional
    @Override
    public void deleteUserById(Long id) {
        log.info("Start method deleteUserById id = {}", id);
        getUser(id).ifPresent(user -> userRepository.deleteById(user.getId()));
    }

    private Optional<User> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("Not found user with id = {}", id);

            throw new BusinessException(USER_NOT_FOUND);
        }
        return user;
    }

}
