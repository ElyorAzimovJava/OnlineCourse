package org.example.onlinecourse.service;

import org.example.onlinecourse.dto.course.CourseRequestDto;
import org.example.onlinecourse.dto.course.CourseResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

public interface CourseService {
    ResponseEntity<CourseResponseDto> addCourse(CourseRequestDto courseRequestDto) throws IOException;

    ResponseEntity<CourseResponseDto> getById(UUID id) throws IOException;

    ResponseEntity<List<CourseResponseDto>> getAllCourses();

    ResponseEntity<CourseResponseDto> updateCourse(UUID id, CourseRequestDto courseRequestDto);
    ResponseEntity<Resource> getImage(UUID id) throws MalformedURLException;


}