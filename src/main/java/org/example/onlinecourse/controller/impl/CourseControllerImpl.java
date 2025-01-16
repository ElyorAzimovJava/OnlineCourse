package org.example.onlinecourse.controller.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.controller.interfaces.CourseController;
import org.example.onlinecourse.dto.course.CourseRequestDto;
import org.example.onlinecourse.dto.course.CourseResponseDto;
import org.example.onlinecourse.service.CourseServiceImpl;
import org.example.onlinecourse.utils.Messages;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseControllerImpl implements CourseController {
    private final CourseServiceImpl courseService;

    @Override
    public ResponseEntity<CourseResponseDto> addCourse(CourseRequestDto courseRequestDto) throws IOException {
       return courseService.addCourse(courseRequestDto);
    }

    @Override
    public ResponseEntity<Resource> getImageById(UUID id) throws IOException, URISyntaxException {
       return courseService.getImage(id);
    }

    @Override
    public ResponseEntity<CourseResponseDto> getById(UUID id) throws IOException {
        return courseService.getById(id);
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @Override
    public ResponseEntity<CourseResponseDto> updateCourse(UUID id, CourseRequestDto courseRequestDto) {
        return courseService.updateCourse(id, courseRequestDto);
    }

}
