package org.example.onlinecourse.controller.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.controller.interfaces.TeacherController;
import org.example.onlinecourse.dto.teacher.TeacherRequestDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.example.onlinecourse.service.TeacherServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeacherControllerImpl implements TeacherController {
    private final TeacherServiceImpl teacherService;
    @Override
    public ResponseEntity<TeacherResponseDto> addTeacher(TeacherRequestDto teacher) {
        return teacherService.addTeacher(teacher);
    }

    @Override
    public ResponseEntity<TeacherResponseDto> getTeacherById(UUID id) {
        return teacherService.getById(id);
    }

    @Override
    public ResponseEntity<List<TeacherResponseDto>> getAll() {
        return teacherService.getAll();
    }

    @Override
    public ResponseEntity<TeacherResponseDto> updateTeacher(UUID id, TeacherRequestDto teacher) {
        return teacherService.updateTeacher(id,teacher);
    }
}
