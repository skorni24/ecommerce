package com.dev.skorni.ecommerce;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
     
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return user.getName() + " has been added to the users list.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
