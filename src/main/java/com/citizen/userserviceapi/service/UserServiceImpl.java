package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.exception.BusinessException;
import com.citizen.userserviceapi.mapper.UserMapper;
import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.dto.UserUpdateDto;
import com.citizen.userserviceapi.model.entity.User;
import com.citizen.userserviceapi.model.kafka.NotificationMessage;
import com.citizen.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.citizen.userserviceapi.model.ExceptionCodes.USER_EXIST_WITH_EMAIL;
import static com.citizen.userserviceapi.model.ExceptionCodes.USER_NOT_FOUND;
import static com.citizen.userserviceapi.model.kafka.NotificationMessages.CREATE_USER_MESSAGE;
import static com.citizen.userserviceapi.model.kafka.NotificationMessages.UPDATE_USER_MESSAGE;

@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "users")
@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.application.name}")
    private String appName;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DogService dogService;
    private final NotificationService notificationService;

    @CachePut(key = "#userDto.firstName")
    @Transactional
    @Override
    public UserDto createUser(UserCreateDto userDto) {
        log.info("Start method createUser {}", userDto);

        verifyEmail(userDto.getEmail());

        User user = userRepository.save(userMapper.toUser(userDto));

        log.info("Saved user with id = {}", user.getId());

        notificationService.sendMessage(new NotificationMessage()
                .setFrom(appName)
                .setTo(user.getEmail())
                .setMessage(CREATE_USER_MESSAGE.getMessage()));

        return userMapper.toUserDto(user);
    }

    @CachePut(key = "#userDto.id")
    @Transactional
    @Override
    public UserDto updateUser(UserUpdateDto userDto) {
        log.info("Start method updateUser {}", userDto);

        verifyEmail(userDto.getEmail());
        dogService.checkDogById(userDto.getDogIds());

        Optional<User> user = getUser(userDto.getId());

        User us = user.get();
        mapDtoToUser(userDto, us);

        log.info("Updated user with id = {}", us.getId());

        userRepository.save(us);

        notificationService.sendMessage(new NotificationMessage()
                .setFrom(appName)
                .setTo(us.getEmail())
                .setMessage(UPDATE_USER_MESSAGE.getMessage()));

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
        getUser(id).ifPresent(user -> {
            userRepository.deleteById(user.getId());

            notificationService.sendMessage(new NotificationMessage()
                    .setFrom(appName)
                    .setTo(user.getEmail())
                    .setMessage(UPDATE_USER_MESSAGE.getMessage()));
        });
    }

    private Optional<User> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("Not found user with id = {}", id);

            throw new BusinessException(USER_NOT_FOUND);
        }
        return user;
    }

    private void verifyEmail(String email) {
        if (StringUtils.isBlank(email)) return;

        Optional<User> userByEmail = userRepository.findByEmail(email);

        if (userByEmail.isPresent()) {
            log.error("User with email {} exist", email);

            throw new BusinessException(USER_EXIST_WITH_EMAIL);
        }
    }

    private void mapDtoToUser(UserUpdateDto userDto, User us) {
        us.setFirstName(userDto.getFirstName());
        us.setLastName(userDto.getLastName());
        us.setDogIds(userDto.getDogIds());
        us.setOrganizationId(userDto.getOrganizationId());
        us.setEmail(userDto.getEmail());
    }

}
