package org.example.onlinecourse.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseResponseDto {
    private UUID id;
    private String name;
    private String description;
    private CourseCategoryResponseDto category;
    private TeacherResponseDto teacher;
    private String imageUrl;
    private Long userCount;
}
