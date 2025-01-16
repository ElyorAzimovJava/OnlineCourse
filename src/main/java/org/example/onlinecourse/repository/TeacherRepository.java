package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Boolean existsByPhoneNumber(String phoneNumber);

}
