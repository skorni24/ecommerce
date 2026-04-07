package com.dev.skorni.ecommerce;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class UserService {
private List<User> usersList = new ArrayList<>();

    public List<User> getAllUsers() {
        return usersList;
    }

    public User getUserById(long id) {
        return usersList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found."));
    }

    public List<User> addUser(User user) {
        if (user.getId() == 0) {
            user.setId(usersList.size() + 1);
         }
         if (user.getName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("User name and email cannot be null.");
         }
        usersList.add(user);
        return usersList;
    }

    public User updateUser(long id, User updatedUser) {
        User user = getUserById(id);
        usersList.remove(user);
        updatedUser.setId(id);
        usersList.add(updatedUser);
        return getUserById(id);
    }
}
