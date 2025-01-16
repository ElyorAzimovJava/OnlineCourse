package org.example.onlinecourse.controller.interfaces;

import jakarta.validation.Valid;
import org.example.onlinecourse.dto.course.CourseRequestDto;
import org.example.onlinecourse.dto.course.CourseResponseDto;
import org.example.onlinecourse.utils.RestConstants;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RequestMapping(CourseController.COURSE_PATH)
public interface CourseController {
    String COURSE_PATH = RestConstants.BASE_PATH + "/course";
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CourseResponseDto> addCourse( @Valid @ModelAttribute  CourseRequestDto courseRequestDto) throws IOException;

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getById/{id}")
    ResponseEntity<CourseResponseDto> getById(@PathVariable UUID id) throws IOException;

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/all")
    ResponseEntity<List<CourseResponseDto>> getAllCourses();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateById/{id}")
    ResponseEntity<CourseResponseDto> updateCourse(@PathVariable UUID id, CourseRequestDto courseRequestDto);


    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/image/{id}")
    @ResponseBody
    ResponseEntity<Resource> getImageById(@PathVariable UUID id) throws MalformedURLException, IOException, URISyntaxException;
}
