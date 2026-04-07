package com.dev.skorni.ecommerce;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User with id " + id + " not found."));
        }
        return ResponseEntity.ok(user);
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
