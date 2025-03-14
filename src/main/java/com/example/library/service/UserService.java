// User service
package com.example.library.service;

import com.example.library.dto.UpdateBookRequest;
import com.example.library.dto.UpdateUserRequest;
import com.example.library.dto.UserRegisterRequest;
import com.example.library.dto.UserResponse;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.model.Role;
import com.example.library.model.User;
import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        for (User user : users) {
            userResponses.add(DtoMapper.createUserToResponse(user));
        }
        return userResponses;
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public UserResponse getUserResponseById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return DtoMapper.createUserToResponse(optionalUser.get());
    }

    public List<UserResponse> getUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName).get();
        List<User> users = userRepository.findByRole(role);
        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        for (User user : users) {
            userResponses.add(DtoMapper.createUserToResponse(user));
        }
        return userResponses;
    }

    public List<UserResponse> getUsersByName(String name){
        List<User> users = userRepository.getUsersByName(name);
        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        for (User user : users) {
            userResponses.add(DtoMapper.createUserToResponse(user));
        }
        return userResponses;
    }

    public List<UserResponse> getUsersBySurname(String surname){
        List<User> users = userRepository.getUsersBySurname(surname);
        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        for (User user : users) {
            userResponses.add(DtoMapper.createUserToResponse(user));
        }
        return userResponses;
    }


    public UserResponse saveUser(UserRegisterRequest userRegisterRequest) {
        Role role = roleRepository.findById(userRegisterRequest.getRole()).orElse(null);
        User user = DtoMapper.registerRequestToUser(userRegisterRequest);
        user.setRole(role);
        return DtoMapper.createUserToResponse(userRepository.save(user));
    }

    public UserResponse updateUser(UpdateUserRequest updateUserRequest, User user) {
        if(updateUserRequest.getUsername() != null) {
            user.setUsername(updateUserRequest.getUsername());
        }
        if(updateUserRequest.getPassword() != null) {
            user.setPassword(updateUserRequest.getPassword());
        }
        if(updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if(updateUserRequest.getRole() != null) {
            user.setRole(roleRepository.findById(updateUserRequest.getRole()).orElse(null));
        }
        if(updateUserRequest.getName() != null) {
            user.setName(updateUserRequest.getName());
        }
        if(updateUserRequest.getSurname() != null) {
            user.setSurname(updateUserRequest.getSurname());
        }
        userRepository.save(user);
        return DtoMapper.createUserToResponse(user);
    }


    public User deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        userRepository.delete(user);
        return user;
    }
}