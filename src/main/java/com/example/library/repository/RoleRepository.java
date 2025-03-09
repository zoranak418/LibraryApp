package com.example.library.repository;

import com.example.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByName(String name);
}
