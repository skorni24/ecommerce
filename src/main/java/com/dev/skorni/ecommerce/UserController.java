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
        return userService.getUserById(id) != null
                ? new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK)
                : new ResponseEntity<>("User with id " + id + " not found.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
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
