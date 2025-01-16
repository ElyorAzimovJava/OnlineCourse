package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String phoneNumber);
    Boolean existsByEmailOrTelegramUsername(String email, String telephoneNumber);
}
