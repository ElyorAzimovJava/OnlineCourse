package org.example.onlinecourse.controller.interfaces;

import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(CourseCategoryController.COURSE_CATEGORY_PATH)
public interface CourseCategoryController {
    String COURSE_CATEGORY_PATH = RestConstants.BASE_PATH_V1+"/category";
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CourseCategoryResponseDto> addCategory(@RequestParam String name);


    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getById/{id}")
    ResponseEntity<CourseCategoryResponseDto> getById(@PathVariable UUID id);

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    ResponseEntity<List<CourseCategoryResponseDto>> getAllCategories();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateById/{id}")
    ResponseEntity<CourseCategoryResponseDto> updateById(@PathVariable UUID id, @RequestParam String name);



}
