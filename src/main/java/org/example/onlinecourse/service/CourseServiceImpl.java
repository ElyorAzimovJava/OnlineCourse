package org.example.onlinecourse.service;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.course.CourseRequestDto;
import org.example.onlinecourse.dto.course.CourseResponseDto;
import org.example.onlinecourse.entities.Course;
import org.example.onlinecourse.entities.CourseCategory;
import org.example.onlinecourse.entities.Teacher;
import org.example.onlinecourse.exceptions.DataAlreadyExists;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.mapper.CourseMapper;
import org.example.onlinecourse.repository.CourseCategoryRepository;
import org.example.onlinecourse.repository.CourseRepository;
import org.example.onlinecourse.repository.TeacherRepository;
import org.example.onlinecourse.utils.Messages;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final CourseCategoryRepository courseCategoryRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;
    @Override
    public ResponseEntity<CourseResponseDto> addCourse(CourseRequestDto courseRequestDto) throws IOException {
        CourseCategory category = courseCategoryRepository.findById(courseRequestDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException(
                        Messages.NOT_FOUND.formatted("CATEGORY", "ID -> " + courseCategoryRepository)
                ));
        Teacher teacher = teacherRepository.findById(courseRequestDto.getTeacherId())
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted
                                ("TEACHER", "BY THIS ID" + courseRequestDto.getTeacherId())));
        if (courseRepository.existsByNameAndCategoryName(
                courseRequestDto.getName(),category.getName()
        )) {
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS.formatted("COURSE NAME AND CATEGORY NAME",
                    courseRequestDto.getName(),category.getName()));
        }

        Course course = courseMapper.toCourse(courseRequestDto);
        course.setCategory(category);
        course.setTeacher(teacher);
        course.setImageUrl(saveImage(courseRequestDto.getImage()));
        courseRepository.save(course);
        return new ResponseEntity<>(courseMapper.toResponseDto(course), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CourseResponseDto> getById(UUID id) throws IOException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted("COURSE", "ID " + id)));
        CourseResponseDto responseDto = courseMapper.toResponseDto(course);
        responseDto.setImageUrl(Messages.BASE_URL + Messages.IMG_URL + course.getImageUrl());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
       return ResponseEntity.ok( courseRepository.findAll()
                .stream()
                .map(course -> {
                    CourseResponseDto responseDto = courseMapper.toResponseDto(course);
                    responseDto.setImageUrl(Messages.BASE_URL + Messages.IMG_URL + course.getImageUrl());
                    return responseDto;
                }).toList());
    }

    @Override
    public ResponseEntity<CourseResponseDto> updateCourse(UUID id, CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Resource> getImage(UUID id) throws MalformedURLException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted("COURSE", "ID " + id)));

        try {
            Path filePath = Paths.get(Messages.IMAGE_PATH+"/"+course.getImageUrl());
            Resource resource = new UrlResource(filePath.toUri());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg")).headers(headers).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public String saveImage(MultipartFile file) throws IOException, IOException {
        Path uploadPath = Paths.get(Messages.IMAGE_PATH);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create directory if it doesn't exist
        }

        // Generate a unique file name
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName); // Extract the file extension
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        // Save the file
        Path filePath = uploadPath.resolve(uniqueFileName);
        file.transferTo(filePath.toFile());

        // Return the file path or name
        return uniqueFileName; // Or return filePath.toString() for full path
    }

    // Helper method to extract the file extension
    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return ""; // Default to no extension
    }
}
