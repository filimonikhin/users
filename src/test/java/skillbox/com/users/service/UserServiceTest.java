package skillbox.com.users.service;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.mapper.UserMapper;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;
import org.junit.jupiter.api.function.Executable;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    SubscriptionRepository subscriptionRepository;
    @InjectMocks
    UserService userService;
    UserEntity testUserEntity;
    UserDto testUserDto;

    @BeforeEach
    void setUp() {
        testUserDto = new UserDto(1, "Test user name", "login", "M", "test@gmail.ru",
                                    "8(495)123-18-25", true, "address", false, 1);
        testUserEntity = UserMapper.dtoToEntity(testUserDto);
    }

    @Test
    void testCreateUser_Success() {
        // given
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUserEntity);
        // when
        UserDto actualUserDto = userService.createUser(testUserDto);
        // then
        assertTrue(new ReflectionEquals(testUserDto).matches(actualUserDto));
    }

    @Test
    void testGetUser_Success() {
        // given
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUserEntity));
        // when
        UserDto actualUserDto = userService.getUser(userId);
        // then
        assertTrue(new ReflectionEquals(testUserDto).matches(actualUserDto));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUser_Fail() {
        // given
        Integer userId = 1;
        when(userRepository.findById(userId)).thenThrow(PersistenceException.class);
        // when
        Executable executable = () -> userService.getUser(userId);
        // then
        assertThrows(PersistenceException.class, executable);
    }

    @Test
    void testGetAllUsers_Success() {
        // given
        List<UserEntity> userEntityList = List.of(testUserEntity);
        when(userRepository.findByDeletedFalse()).thenReturn(userEntityList);
        // when
        List<UserDto> usersDto = userService.getAllUsers();
        // then
        assertEquals(1, usersDto.size());
        verify(userRepository, times(1)).findByDeletedFalse();
    }

    @Test
    void testGetUserByLogin_Success() {
        // given
        String login = "login";
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(testUserEntity));
        // when
        UserDto actualUserDto = userService.getUserByLogin(login);
        UserDto expectedUserDto = testUserDto.clone();
        expectedUserDto.setLogin(login);
        // then
        assertTrue(new ReflectionEquals(expectedUserDto).matches(actualUserDto));
    }

    @Test
    void testGetUserByLogin_Fail() {
        // given
        String login = "login";
        when(userRepository.findByLogin(login)).thenThrow(PersistenceException.class);
        // when
        Executable executable = () -> userService.getUserByLogin(login);
        // then
        assertThrows(PersistenceException.class, executable);
    }

    @Test
    void testUpdateUser_Success() {
        // given
        Integer userId = 1;
        UserDto modifyUserDto = testUserDto.clone();
        modifyUserDto.setName("New test name");
        when(userRepository.existsById(userId)).thenReturn(Boolean.TRUE);
        when(userRepository.save(any(UserEntity.class))).thenReturn(UserMapper.dtoToEntity(modifyUserDto));
        // when
        boolean isUpdated = userService.updateUser(modifyUserDto, userId);
        // then
        assertTrue(isUpdated);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testDeleteUser_whenUserFound_Success() {
        // given
        Integer userId = 1;
        when(userRepository.existsById(userId)).thenReturn(Boolean.TRUE);
        // when
        boolean isDeleted = userService.deleteUser(userId);
        // then
        //assertTrue(isDeleted);
        verify(userRepository, times(1)).deleteUserById(userId);
        verify(subscriptionRepository, times(1)).deleteSubscriptionByUserId(userId);
    }

    @Test
    void testDeleteUser_whenUserNotFound_Success() {
        // given
        Integer userId = 1;
        when(userRepository.existsById(userId)).thenReturn(Boolean.FALSE);
        // when
        boolean isDeleted = userService.deleteUser(userId);
        // then
        //assertTrue(isDeleted);
        verify(userRepository, never()).deleteUserById(userId);
        verify(subscriptionRepository, never()).deleteSubscriptionByUserId(userId);
    }
}