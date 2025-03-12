// User controller
package com.example.library.controller;

import com.example.library.dto.UpdateBookRequest;
import com.example.library.dto.UpdateUserRequest;
import com.example.library.dto.UserRegisterRequest;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Endpoints for managing users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique identifier")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/register")
    public User createUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.saveUser(userRegisterRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        if(userService.getUserById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userService.updateUser(updateUserRequest, user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}