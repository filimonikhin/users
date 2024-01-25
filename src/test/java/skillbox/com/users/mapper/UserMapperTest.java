package skillbox.com.users.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    UserEntity testUserEntity;
    UserDto testUserDto;

    @BeforeEach
    void setUp() {
        testUserEntity = new UserEntity(1, "Test user name", "login", "M",
                "test@gmail.ru", "8(495)123-18-25", true, "address", false, 1);
        testUserDto = new UserDto(1, "Test user name", "login", "M",
                "test@gmail.ru","8(495)123-18-25", true, "address", false, 1);
    }

    @Test
    void testDtoToEntity_Success() {
        // given
        UserEntity expectedUserEntity = testUserEntity;
        // when
        UserEntity actualUserEntity = UserMapper.dtoToEntity(testUserDto);
        // then
        assertTrue(new ReflectionEquals(expectedUserEntity).matches(actualUserEntity));
    }

    @Test
    void testEntityToDto_Success() {
        // given
        UserDto expectedUserDto = testUserDto;
        // when
        UserDto actualUserDto = UserMapper.entityToDto(testUserEntity);
        // then
        assertTrue(new ReflectionEquals(expectedUserDto).matches(actualUserDto));
    }
}