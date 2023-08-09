package com.citizen.userserviceapi.mapper;

import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreateDto dto) {
        return new User()
                .setFirstName(dto.firstName())
                .setLastName(dto.lastName());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDogIds(),
                user.getOrganizationId()
        );
    }

}
