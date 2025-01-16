package org.example.onlinecourse.service;

import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CourseCategoryService {
    ResponseEntity<CourseCategoryResponseDto> add(String name);
    ResponseEntity<CourseCategoryResponseDto> getById(UUID id);
    ResponseEntity<List<CourseCategoryResponseDto>> getAll();
    ResponseEntity<CourseCategoryResponseDto> updateById(UUID id, String name);
}
