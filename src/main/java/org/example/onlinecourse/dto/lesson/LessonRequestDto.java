package org.example.onlinecourse.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequestDto {
    private String name;
    private String description;
    private UUID courseId;
    private MultipartFile multipartFile;
    private Integer number;
}
