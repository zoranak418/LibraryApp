// User service
package com.example.library.service;

import com.example.library.dto.UpdateBookRequest;
import com.example.library.dto.UpdateUserRequest;
import com.example.library.dto.UserRegisterRequest;
import com.example.library.mapper.DtoMapper;
import com.example.library.model.Book;
import com.example.library.model.Role;
import com.example.library.model.User;
import com.example.library.repository.RoleRepository;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User saveUser(UserRegisterRequest userRegisterRequest) {
        Role role = roleRepository.findById(userRegisterRequest.getRole()).orElse(null);
        User user = DtoMapper.registerRequestToUser(userRegisterRequest);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User updateUser(UpdateUserRequest updateUserRequest, User user) {
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
        return user;
    }


    public User deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        userRepository.delete(user);
        return user;
    }
}