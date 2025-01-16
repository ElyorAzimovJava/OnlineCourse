package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    Boolean existsByNameAndCourseId(String name, UUID courseId);
    Boolean existsByNumberAndCourseId(Integer number, UUID courseId);
}
