package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.mapper.UserMapper;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
// import org.springframework.util.ReflectionUtils;
// import java.lang.reflect.Field;
// import java.util.Map;
// import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       SubscriptionRepository subscriptionRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.dtoToEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.entityToDto(savedUser);
        //return String.format("Пользователь %s добавлен в базу с id = %s", savedUser.getName(), savedUser.getId());
    }

    public UserDto getUser(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::entityToDto)
                .orElse(null);
        //return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UserDto getUserByLogin(String userLogin) {
        return  userRepository.findByLogin(userLogin)
                .map(userMapper::entityToDto)
                .orElse(null);
        //return userRepository.findByLogin(userLogin).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean updateUser(UserDto userDto, Integer userId) {
        if (!userRepository.existsById(userId)){
            return false;
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (userDto.getId() == null) {
            userDto.setId(userId);
        }

        UserEntity savedUser = userRepository.save(userMapper.dtoToEntity(userDto));
        return true;
        // return String.format("Пользователь %s успешно обновлен", savedUser.getName());
    }

    public boolean deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)){
            return false;
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // soft удаление - для пользователя проставляется маркер удаления
        userRepository.deleteUserById(userId);

        // soft удаление подписок этого пользователя
        subscriptionRepository.deleteSubscriptionByUserId(userId);

        return true;

        //return String.format("Пользователь с id = %s успешно удален", userId);
    }

    public boolean deleteUserByLogin(String userLogin) {
        if (!userRepository.existsByLogin(userLogin)) {
            return false;
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteByUserLogin(userLogin);
        return true;
        // return String.format("Пользователь с login = %s успешно удален", userLogin);
    }

    public List<UserDto> getAllUsers() {
        return userRepository./*findAllByOrderByIdAsc()*/findByDeletedFalse().stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

}
