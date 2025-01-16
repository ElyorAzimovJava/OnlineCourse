package org.example.onlinecourse.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LessonResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String number;
    private String videoFilePath;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
