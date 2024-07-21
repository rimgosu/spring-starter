package com.clrobur.spring_test.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clrobur.spring_test.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}