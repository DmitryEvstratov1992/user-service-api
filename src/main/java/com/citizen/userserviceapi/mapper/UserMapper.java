package com.citizen.userserviceapi.mapper;

import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreateDto dto) {
        return new User()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName());
    }

    public UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setDogIds(user.getDogIds())
                .setOrganizationId(user.getOrganizationId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

}
