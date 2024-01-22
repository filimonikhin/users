package skillbox.com.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skillbox.com.users.dto.UserDto;
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
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserDto userDto = userService.getUser(userId);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/login/{userLogin}")
    ResponseEntity<UserDto> getUserByLogin(@PathVariable String userLogin) {
        UserDto userDto = userService.getUserByLogin(userLogin);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
        boolean updated = userService.updateUser(userDto, userId);

        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId) {
        boolean deleted =  userService.deleteUser(userId);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/login/{userLogin}")
     ResponseEntity<Boolean> deleteUserByLogin(@PathVariable String userLogin) {
        boolean deleted = userService.deleteUserByLogin(userLogin);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
