package org.example.onlinecourse.controller.interfaces;

import jakarta.validation.Valid;
import org.example.onlinecourse.dto.teacher.TeacherRequestDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.example.onlinecourse.entities.Teacher;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(TeacherController.TEACHER_PATH)
public interface TeacherController {
    String TEACHER_PATH = RestConstants.BASE_PATH_V1+"/teacher";

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<TeacherResponseDto> addTeacher(@RequestBody @Valid TeacherRequestDto teacher);

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getById/{id}")
    ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable UUID id);

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/allTeachers")
    ResponseEntity<List<TeacherResponseDto>> getAll();

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/updateById/{id}")
    ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable UUID id, @RequestBody @Valid TeacherRequestDto teacher);


}
