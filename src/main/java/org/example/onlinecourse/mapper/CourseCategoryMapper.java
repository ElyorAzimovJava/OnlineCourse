package org.example.onlinecourse.mapper;

import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.entities.CourseCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseCategoryMapper {
    CourseCategoryResponseDto toResponseDto(CourseCategory courseCategory);
}
