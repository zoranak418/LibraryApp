// User repository
package com.example.library.repository;

import com.example.library.model.Role;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public List<User> getUsersByName(String name);
    public List<User> getUsersByRole(Role role);
    public List<User> getUsersBySurname(String surname);
    public Optional<User> findByUsername(String username);

    List<User> findByRole(Role role);
}