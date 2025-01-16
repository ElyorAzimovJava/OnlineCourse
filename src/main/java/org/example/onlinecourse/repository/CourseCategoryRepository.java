package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, UUID> {
    Boolean existsByName(String name);
}
