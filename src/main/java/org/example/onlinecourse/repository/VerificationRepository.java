package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.baseEntity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VerificationRepository extends JpaRepository<Verification, UUID> {
    Verification findByEmail(String email);

}
