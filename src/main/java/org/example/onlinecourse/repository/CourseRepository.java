package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Boolean existsByNameAndCategoryName(String name, String categoryName);
}
