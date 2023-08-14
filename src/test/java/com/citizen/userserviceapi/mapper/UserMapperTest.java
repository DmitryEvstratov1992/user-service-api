package com.citizen.userserviceapi.mapper;

import com.citizen.userserviceapi.model.dto.UserCreateDto;
import com.citizen.userserviceapi.model.dto.UserDto;
import com.citizen.userserviceapi.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @Test
    public void toUserTest() {
        UserCreateDto userCreateDto = new UserCreateDto("dev", "prod");

        User user = new UserMapper().toUser(
                userCreateDto
        );

        assertEquals(userCreateDto.getFirstName(), user.getFirstName());
        assertEquals(userCreateDto.getLastName(), user.getLastName());
    }

    @Test
    public void toUserDtoTest() {
        User user = new User()
                .setFirstName("Pavel")
                .setLastName("Egorov")
                .setDogIds(Set.of(1L, 2L, 3L))
                .setOrganizationId(422L);

        UserDto userDto = new UserMapper().toUserDto(user);

        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getOrganizationId(), userDto.getOrganizationId());
        assertEquals(user.getDogIds(), userDto.getDogIds());
    }

}
