// User controller
package com.example.library.controller;

import com.example.library.dto.UserRegisterRequest;
import com.example.library.model.User;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        System.out.println("hello");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/register")
    public User createUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.saveUser(userRegisterRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}