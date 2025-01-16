package org.example.onlinecourse.service;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.courseCategory.CourseCategoryResponseDto;
import org.example.onlinecourse.entities.CourseCategory;
import org.example.onlinecourse.exceptions.DataAlreadyExists;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.mapper.CourseCategoryMapper;
import org.example.onlinecourse.repository.CourseCategoryRepository;
import org.example.onlinecourse.utils.Messages;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseCategoryMapper courseCategoryMapper;

    @Override
    public ResponseEntity<CourseCategoryResponseDto> add(String name) {
        if (courseCategoryRepository.existsByName(name)) {
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS
                    .formatted("CATEGORY","NAME -> "+name));
        }
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setName(name);
        courseCategoryRepository.save(courseCategory);
        return ResponseEntity.ok(courseCategoryMapper.toResponseDto(courseCategory));
    }

    @Override
    public ResponseEntity<CourseCategoryResponseDto> getById(UUID id) {
        return ResponseEntity.ok(courseCategoryMapper.toResponseDto(
                courseCategoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        Messages.NOT_FOUND.formatted("CATEGORY", "ID -> " + id)
                ))));
    }

    @Override
    public ResponseEntity<List<CourseCategoryResponseDto>> getAll() {
        return ResponseEntity.ok(courseCategoryRepository.findAll()
                .stream()
                .map(courseCategoryMapper::toResponseDto)
                .toList());
    }

    @Override
    public ResponseEntity<CourseCategoryResponseDto> updateById(UUID id, String name) {
        if (courseCategoryRepository.existsByName(name)) {
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS
                    .formatted("CATEGORY","NAME -> "+name));
        }
        CourseCategory category = courseCategoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        Messages.NOT_FOUND.formatted("CATEGORY", "ID -> " + id)
                ));

        category.setName(name);

        return ResponseEntity.ok(courseCategoryMapper.toResponseDto(
                courseCategoryRepository.save(category)
        ));
    }
}
