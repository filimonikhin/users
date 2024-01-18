package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;
import java.util.List;
// import org.springframework.util.ReflectionUtils;
// import java.lang.reflect.Field;
// import java.util.Map;
// import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserService(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public String createUser(UserEntity userEntity) {
        UserEntity savedUser = userRepository.save(userEntity);
        return String.format("Пользователь %s добавлен в базу с id = %s", savedUser.getName(), savedUser.getId());
    }

    public UserEntity getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /* если нужно передавать все поля Entity в JSON (но не сработает, если в JSON передаем только изменяемое поле)
    */
    public String updateUser(UserEntity userEntity, Integer userId) {
            if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (userEntity.getId() == null) userEntity.setId(userId);

        UserEntity savedUser = userRepository.save(userEntity);
        return String.format("Пользователь %s успешно обновлен", savedUser.getName());
    }

    // если в запросе JSON передается тоько изменяемое поле
    /*
    public String updateUser(Map<String, Object> fields, Integer userId) {

        Optional<UserEntity> existingUser = userRepository.findById(userId);

        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = existingUser.get();

        fields.forEach ((fieldName, fieldValue) -> {
            // поис поля в классе
            // System.out.println ("field name: " + fieldName + "\n field value: " + fieldValue);
            Field field = ReflectionUtils.findField (userEntity.getClass(), fieldName);

            if (field != null) {
                // делаем доступным для изменения private поля
                field.setAccessible (true);

                // установка значения fieldValue для поля field объекта userEntity
                ReflectionUtils.setField (field, userEntity, fieldValue);

                userRepository.save (userEntity);
            }
        });

        return String.format("Пользователь %s успешно обновлен", userEntity.getName());
    }
    */

    public String deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // soft удаление - для пользователя проставляется маркер удаления
        userRepository.deleteUserById(userId);

        // soft удаление подписок этого пользователя
        subscriptionRepository.deleteSubscriptionByUserId(userId);

        return String.format("Пользователь с id = %s успешно удален", userId);
    }

    public String deleteUserByLogin(String userLogin) {
        if (!userRepository.existsByLogin(userLogin)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteByUserLogin(userLogin);
        return String.format("Пользователь с login = %s успешно удален", userLogin);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers = userRepository.findAllByOrderByIdAsc();
        return allUsers;
    }

    public UserEntity getUserByLogin(String userLogin) {
        return userRepository.findByLogin(userLogin).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
