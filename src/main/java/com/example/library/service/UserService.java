// User service
package com.example.library.service;

import com.example.library.dto.UserRegisterRequest;
import com.example.library.mapper.DtoMapper;
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
        User user = DtoMapper.registerRequestToUser(userRegisterRequest);
        Role role = roleRepository.findById(userRegisterRequest.getRoleId()).orElse(null);
        System.out.println(role);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        userRepository.delete(user);
        return user;
    }
}