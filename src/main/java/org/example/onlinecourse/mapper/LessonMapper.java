package org.example.onlinecourse.mapper;

import org.example.onlinecourse.dto.lesson.LessonRequestDto;
import org.example.onlinecourse.dto.lesson.LessonResponseDto;
import org.example.onlinecourse.entities.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    Lesson toEntity(LessonRequestDto requestDto);
    LessonResponseDto toResponse(Lesson lesson);
}
