package org.example.onlinecourse.service;

import org.example.onlinecourse.dto.teacher.TeacherRequestDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.example.onlinecourse.entities.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    ResponseEntity<TeacherResponseDto> addTeacher(TeacherRequestDto requestDto);
    ResponseEntity<TeacherResponseDto> getById(UUID id);
    ResponseEntity<List<TeacherResponseDto>> getAll();
    ResponseEntity<TeacherResponseDto> updateTeacher(UUID id,TeacherRequestDto requestDto);
}
