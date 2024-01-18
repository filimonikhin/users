package skillbox.com.users.controller;

import org.springframework.web.bind.annotation.*;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.service.UserService;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    String createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping(path = "/{userId}")
    UserEntity getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping(path = "/login/{userLogin}")
    UserEntity getUserByLogin(@PathVariable String userLogin) {
        return userService.getUserByLogin(userLogin);
    }

    /* метод работает, когда в JSON запросе передаем только изменяемое поле
    @PatchMapping("/{userId}")
    String UpdateUser(@RequestBody Map<String, Object> fields, @PathVariable Integer userId) {
        return userService.updateUser(fields, userId);
    }
    */

    /* если нужно передавать все поля Entity в JSON (не сработает, если в JSON передаем только изменяемое поле
       т.к. в этом случает в Entity другие поля будут = null)
    */
    @PatchMapping("/{userId}")
    String updateUser(@RequestBody UserEntity userEntity, @PathVariable Integer userId) {
        return userService.updateUser(userEntity, userId);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }

    @DeleteMapping("/login/{userLogin}")
    String deleteUserByLogin(@PathVariable String userLogin) {
        return userService.deleteUserByLogin(userLogin);
    }

    @GetMapping()
    List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

}
