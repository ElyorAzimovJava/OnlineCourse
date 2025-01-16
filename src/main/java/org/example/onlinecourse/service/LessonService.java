package org.example.onlinecourse.service;

import org.example.onlinecourse.dto.lesson.LessonRequestDto;
import org.example.onlinecourse.dto.lesson.LessonResponseDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface LessonService {
    ResponseEntity<LessonResponseDto> addLesson(LessonRequestDto requestDto) throws IOException;
    ResponseEntity<LessonResponseDto> getById(UUID id);
    ResponseEntity<List<LessonResponseDto>>getAllLessonsByCourseId(UUID courseId);
    ResponseEntity<LessonResponseDto> updateLesson(UUID id, LessonRequestDto requestDto);
    ResponseEntity<LessonResponseDto> deleteLesson(UUID id);
}
