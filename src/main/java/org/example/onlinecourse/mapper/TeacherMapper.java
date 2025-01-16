package org.example.onlinecourse.mapper;

import org.example.onlinecourse.dto.teacher.TeacherRequestDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.example.onlinecourse.entities.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toTeacher(TeacherRequestDto requestDto);
    TeacherResponseDto toDto(Teacher teacher);
}
