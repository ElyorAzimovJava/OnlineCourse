package org.example.onlinecourse.dto.course;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private UUID teacherId;
    @NotNull(message = "")
    private MultipartFile image;

}

