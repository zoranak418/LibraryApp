// User controller
package com.example.library.controller;

import com.example.library.dto.UpdateBookRequest;
import com.example.library.dto.UpdateUserRequest;
import com.example.library.dto.UserRegisterRequest;
import com.example.library.dto.UserResponse;
import com.example.library.model.Book;
import com.example.library.model.Role;
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
    public List<UserResponse> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique identifier")
    public UserResponse getUserById(Long id) {
        return userService.getUserResponseById(id);
    }

    @GetMapping("/names/{name}")
    @Operation(summary = "Get users by name", description = "Retrieve a list of users search by name")
    public List<UserResponse> getUserByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/surnames/{surname}")
    @Operation(summary = "Get users by surname", description = "Retrieve a list of users search by surname")
    public List<UserResponse> getUserBySurname(@PathVariable String surname) {
        return userService.getUsersBySurname(surname);
    }

    @GetMapping("/roles/{roleName}")
    @Operation(summary = "Get users by role name", description = "Retrieve a list of users with specific role")
    public List<UserResponse> getUsersByRole(@PathVariable String roleName) {
        return userService.getUsersByRole(roleName);
    }


    @PostMapping(value = "/register")
    public UserResponse createUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.saveUser(userRegisterRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        User user = userService.getUserById(id);
        return userService.updateUser(updateUserRequest, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}