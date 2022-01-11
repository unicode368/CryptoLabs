package com.example.lab5.repository;

import com.example.lab5.entity.Role;
import com.example.lab5.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<Set<User>> findAllByRoles(Role role);
}
