package org.example.onlinecourse.controller.impl;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.controller.interfaces.CourseCategoryController;
import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.service.CourseCategoryServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseCategoryControllerImpl implements CourseCategoryController {
    private final CourseCategoryServiceImpl courseCategoryService;


    @Override
    public ResponseEntity<CourseCategoryResponseDto> addCategory(String name) {
        return courseCategoryService.add(name);
    }

    @Override
    public ResponseEntity<CourseCategoryResponseDto> getById(UUID id) {
        return courseCategoryService.getById(id);
    }

    @Override
    public ResponseEntity<List<CourseCategoryResponseDto>> getAllCategories() {
        return courseCategoryService.getAll();
    }

    @Override
    public ResponseEntity<CourseCategoryResponseDto> updateById(UUID id, String name) {
        return courseCategoryService.updateById(id, name);
    }
}
