package com.citizen.userserviceapi.mapper;

import com.citizen.userserviceapi.mapper.UserMapper;
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

        assertEquals(userCreateDto.firstName(), user.getFirstName());
        assertEquals(userCreateDto.lastName(), user.getLastName());
    }

    @Test
    public void toUserDtoTest() {
        User user = new User()
                .setFirstName("Pavel")
                .setLastName("Egorov")
                .setDogIds(Set.of(1L, 2L, 3L))
                .setOrganizationId(422L);

        UserDto userDto = new UserMapper().toUserDto(user);

        assertEquals(user.getFirstName(), userDto.firstName());
        assertEquals(user.getLastName(), userDto.lastName());
        assertEquals(user.getOrganizationId(), userDto.organizationId());
        assertEquals(user.getDogIds(), userDto.dogIds());
    }

}
