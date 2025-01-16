package org.example.onlinecourse.controller.interfaces;

import jakarta.validation.Valid;
import org.example.onlinecourse.dto.lesson.LessonRequestDto;
import org.example.onlinecourse.dto.lesson.LessonResponseDto;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RequestMapping(LessonController.LESSON_PATH)
public interface LessonController {
    String LESSON_PATH = RestConstants.BASE_PATH_V1 +"/lesson";
    @PostMapping(name = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<LessonResponseDto> addLessonToCourse(@Valid @ModelAttribute LessonRequestDto lessonRequestDto) throws IOException;

    @GetMapping("/getByCourseId/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    ResponseEntity<LessonResponseDto> getLessonByCourseId(@PathVariable UUID id);

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    ResponseEntity<LessonResponseDto> updateLesson(@PathVariable UUID id, @Valid @RequestBody LessonRequestDto lessonRequestDto);

}
