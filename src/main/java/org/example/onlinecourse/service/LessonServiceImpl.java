package org.example.onlinecourse.service;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.lesson.LessonRequestDto;
import org.example.onlinecourse.dto.lesson.LessonResponseDto;
import org.example.onlinecourse.entities.Course;
import org.example.onlinecourse.entities.Lesson;
import org.example.onlinecourse.exceptions.DataAlreadyExists;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.mapper.LessonMapper;
import org.example.onlinecourse.repository.CourseRepository;
import org.example.onlinecourse.repository.LessonRepository;
import org.example.onlinecourse.utils.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final CourseRepository courseRepository;
    @Override
    public ResponseEntity<LessonResponseDto> addLesson(LessonRequestDto requestDto) throws IOException {
        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted("COURSE", "ID " + requestDto.getCourseId())));
        if(lessonRepository.existsByNameAndCourseId(requestDto.getName(), requestDto.getCourseId())){
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS
            .formatted("NAME IN COURSE", requestDto.getName()));
        }
        if(lessonRepository.existsByNumberAndCourseId(requestDto.getNumber(), requestDto.getCourseId())){
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS
                    .formatted("NUMBER IN COURSE", requestDto.getNumber()));
        }
        Lesson entity = lessonMapper.toEntity(requestDto);
        entity.setCourse(course);
        entity.setVideoFilePath(saveLessonVideo(requestDto.getMultipartFile()));
        return  new ResponseEntity<>(lessonMapper.toResponse(
                lessonRepository.save(entity)), HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<LessonResponseDto> getById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<LessonResponseDto>> getAllLessonsByCourseId(UUID courseId) {
        return null;
    }

    @Override
    public ResponseEntity<LessonResponseDto> updateLesson(UUID id, LessonRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<LessonResponseDto> deleteLesson(UUID id) {
        return null;
    }
    private String saveLessonVideo(MultipartFile file) throws IOException, IOException {
        Path uploadPath = Paths.get(Messages.VIDEO_PATH);
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
