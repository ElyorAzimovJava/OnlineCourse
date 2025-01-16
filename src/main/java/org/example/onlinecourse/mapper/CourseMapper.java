package org.example.onlinecourse.mapper;

import org.example.onlinecourse.dto.course.CourseRequestDto;
import org.example.onlinecourse.dto.course.CourseResponseDto;
import org.example.onlinecourse.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CourseRequestDto requestDto);
    CourseResponseDto toResponseDto(Course course);
}
