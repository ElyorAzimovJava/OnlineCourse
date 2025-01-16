package org.example.onlinecourse.controller.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.controller.interfaces.LessonController;
import org.example.onlinecourse.dto.lesson.LessonRequestDto;
import org.example.onlinecourse.dto.lesson.LessonResponseDto;
import org.example.onlinecourse.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class LessonControllerImpl implements LessonController {
    private final LessonService lessonService;
    @Override
    public ResponseEntity<LessonResponseDto> addLessonToCourse(LessonRequestDto lessonRequestDto) throws IOException {
        return lessonService.addLesson(lessonRequestDto);
    }

    @Override
    public ResponseEntity<LessonResponseDto> getLessonByCourseId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<LessonResponseDto> updateLesson(UUID id, LessonRequestDto lessonRequestDto) {
        return null;
    }
}
